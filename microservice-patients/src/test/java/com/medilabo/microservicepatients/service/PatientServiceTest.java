package com.medilabo.microservicepatients.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.medilabo.microservicepatients.model.Patient;

@SpringBootTest
class PatientServiceTest {

	@MockBean
	private PatientService patientServiceUnderTest;

	private Patient patientTest;

	@BeforeEach
	void setPerTest() {
		patientTest = new Patient();
		patientTest.setId(6);
		patientTest.setPrenom("prenomtest2");
		patientTest.setNom("nomtest2");
		patientTest.setDateDeNaissance("1970-01-01");
		patientTest.setAdresse("33 Hazelton Avenue 2nd Floor Toronto,");
		patientTest.setTelephone("778-945-3170");
		patientTest.setGenre("F");
		List<Patient> allPatients = new ArrayList<>();
		allPatients.add(patientTest);
		when(patientServiceUnderTest.addPatient(patientTest)).thenReturn(patientTest);
		when(patientServiceUnderTest.getAllPatients()).thenReturn(allPatients);
		when(patientServiceUnderTest.getPatientById(6)).thenReturn(patientTest);
	}

	@Test
	void testAddPatient() throws Exception {
		Patient patient = new Patient();
		patient.setId(5);
		patient.setPrenom("prenomtest");
		patient.setNom("nomtest");
		patient.setDateDeNaissance("1970-01-01");
		patient.setAdresse("2540, boulevard Daniel-Johnson");
		patient.setTelephone("450-978-9555");
		patient.setGenre("F");
		when(patientServiceUnderTest.addPatient(any(Patient.class))).thenReturn(patient);

		try {
			Patient resultPatientCreated = patientServiceUnderTest.addPatient(patient);

			assertEquals(patient, resultPatientCreated);
		} catch (AssertionError e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testAddPatientDuplicated() throws Exception {

		try {
			patientServiceUnderTest.addPatient(patientTest);

			List<Patient> allPatients = patientServiceUnderTest.getAllPatients();
			assertTrue(allPatients.stream().filter(patient -> patient.getId() == 6).count() ==1);
		} catch (IllegalArgumentException e) {
			assertThrows(IllegalArgumentException.class, () -> patientServiceUnderTest.addPatient(patientTest));
		} catch (AssertionError e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testupdatePatient() throws Exception {
		String	existingPatientbirthDate= patientServiceUnderTest.getPatientById(6).getDateDeNaissance();
		assertEquals(existingPatientbirthDate,patientTest.getDateDeNaissance());
	    
		try {	  
			patientTest.setDateDeNaissance("1970-01-02");
			patientServiceUnderTest.updatePatient(patientTest, 6);
			Patient patientFoundByIdUpdated= patientServiceUnderTest.getPatientById(6);
		
			assertNotEquals(patientFoundByIdUpdated.getDateDeNaissance(),existingPatientbirthDate);
		} catch (AssertionError e) {
			fail(e.getMessage());
		}
		
	}

	@Test
	void testupdatePatient_WithNoExistingPatient() throws Exception {
		when(patientServiceUnderTest.getPatientById(1)).thenThrow(NullPointerException.class);
	    
		try {	  
			patientTest.setDateDeNaissance("1970-01-02");
			patientServiceUnderTest.updatePatient(patientTest, 1);
			Patient patientFoundByIdUpdated= patientServiceUnderTest.getPatientById(1);
		
			assertNull(patientFoundByIdUpdated);
		} catch (NullPointerException e) {
			assertThrows(NullPointerException.class, () -> patientServiceUnderTest.getPatientById(1));
		}catch (AssertionError e) {
			fail(e.getMessage());
		}
		
	}
}
