package com.medilabo.microservicepatients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.medilabo.microservicepatients.model.Patient;
import com.medilabo.microservicepatients.repository.PatientRepositoryImpl;

@SpringBootApplication
public class MicroservicePatientsApplication implements CommandLineRunner {

	@Autowired
	private PatientRepositoryImpl patientRepositoryImpl;

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePatientsApplication.class, args);
	}

	// for test CRUD operation
	@Override
	public void run(String... args) {
		List<Patient> allPatients = patientRepositoryImpl.getAllPatients();
		System.out.println("patient" + allPatients);
		allPatients.forEach(patient -> System.out.println("patient" + patient));

		Patient patient = new Patient();
		patient.setPrenom("prenomtest");
		patient.setNom("nomtest");
		patient.setDateDeNaissance("0000-00-00");
		patient.setAdresse("7557 eregtrhrthrthet");
		patient.setTelephone("755-778-6746");
		patient.setGenre("F");
		patientRepositoryImpl.addPatient(patient);
		patient.setPrenom("prenomtestUpdated");
		
		patientRepositoryImpl.updatePatient(patient, 1);
		String prenomModifie=patientRepositoryImpl.getPatientById( 1).getPrenom();
		System.out.println("prenomModifie" +prenomModifie);

	}

}
