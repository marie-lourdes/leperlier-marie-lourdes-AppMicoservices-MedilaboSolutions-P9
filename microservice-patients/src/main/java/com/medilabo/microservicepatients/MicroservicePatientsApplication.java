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
	private PatientRepositoryImpl patientRepositoryImpl ;
	
	public static void main(String[] args) {
		SpringApplication.run(MicroservicePatientsApplication.class, args);
	}

	@Override
	public void run(String ... args) {
		List<Patient> allPatients=patientRepositoryImpl.getAllPatients(); 
		System.out.println("patient"+allPatients);
		allPatients.forEach(patient-> System.out.println("patient"+patient));
		
		Patient patient=new Patient();
		patient.setDateDeNaissance("0000-00-00");
		patient.setAdresse("7557 eregtrhrthrthet");
		patient.setTelephone("755-778-6746");
		patient.setGenre("F");
				patientRepositoryImpl.addPatient(patient); 
	}
}
