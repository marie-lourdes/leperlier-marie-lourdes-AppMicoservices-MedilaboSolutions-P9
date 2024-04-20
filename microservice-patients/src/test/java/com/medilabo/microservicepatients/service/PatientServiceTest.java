package com.medilabo.microservicepatients.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.medilabo.microservicepatients.model.Patient;

@SpringBootTest
class PatientServiceTest {

	@MockBean
	private PatientService patientServiceUnderTest;

	private Patient patient2;

	@BeforeEach
	void setPerTest() {
		patient2 = new Patient();
		patient2.setId(6);
		patient2.setPrenom("prenomtest2");
		patient2.setNom("nomtest2");
		patient2.setDateDeNaissance("1970-01-01");
		patient2.setAdresse("33 Hazelton Avenue 2nd Floor Toronto,");
		patient2.setTelephone("778-945-3170");
		patient2.setGenre("F");
		List<Patient> allPatients = new ArrayList<>();
		allPatients.add(patient2);
		when(patientServiceUnderTest.addPatient(patient2)).thenReturn(patient2);
		when(patientServiceUnderTest.getAllPatients()).thenReturn(allPatients);
		// patientServiceUnderTest.addPatient(patient);
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
			patientServiceUnderTest.addPatient(patient2);

			List<Patient> allPatients = patientServiceUnderTest.getAllPatients();
			assertTrue(allPatients.stream().filter(patient -> patient.getId() == 6).count() ==1);
		} catch (IllegalArgumentException e) {
			assertThrows(IllegalArgumentException.class, () -> patientServiceUnderTest.addPatient(patient2));
		} catch (AssertionError e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testupdatePatient() throws Exception {}
}
