package com.medilabo.microservicepatients.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.medilabo.microservicepatients.model.Patient;

@SpringBootTest
class PatientRepositoryImplTest {
	
    @MockBean
	private PatientRepositoryImpl patientRepositoryImplUnderTest = new  PatientRepositoryImpl() ;
	
	private Patient patient;
	
	@BeforeEach
	void setPerTest() {
		patient = new Patient();
		patient.setPrenom("prenomtest");
		patient.setNom("nomtest");
		patient.setDateDeNaissance("1970-01-01");
		patient.setAdresse("2540, boulevard Daniel-Johnson");
		patient.setTelephone("450-978-9555");
		patient.setGenre("F");
		patientRepositoryImplUnderTest.addPatient(patient);
	}
	
	@Test
	void testAddPatient() throws Exception{
	//	when(patientRepositoryImpl.addPatient(any(Patient.class))).thenReturn(patient);
		try {
			Patient resultPatientCreated=patientRepositoryImplUnderTest.addPatient(patient) ;
	     assertTrue(patientRepositoryImplUnderTest.getPatientById(resultPatientCreated.getId())!=null);
		}catch(AssertionError e) {
			fail(e.getMessage());
		}
	}
	
	
/*	@Test
	void testAddPatientDuplicated() throws Exception{
		when(patientRepositoryImpl.addPatient(any(Patient.class))).thenReturn(patient);
		try {
			
		}catch(IllegalArgumentException e) {
			assertThrows(IllegalArgumentException.class,
					() ->patientRepositoryImpl.addPatient(any(Patient.class)));
		}catch(AssertionError e) {
			fail(e.getMessage());
		}
		
	}*/

}
