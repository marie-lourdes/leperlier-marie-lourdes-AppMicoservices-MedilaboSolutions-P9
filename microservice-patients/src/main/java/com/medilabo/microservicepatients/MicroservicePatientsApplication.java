package com.medilabo.microservicepatients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.medilabo.microservicepatients.model.Genre;
import com.medilabo.microservicepatients.model.Patient;
import com.medilabo.microservicepatients.repository.IPatientRepository;
import com.medilabo.microservicepatients.service.GenrePatientService;

import jakarta.transaction.Transactional;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicePatientsApplication implements CommandLineRunner{
	@Autowired
	private GenrePatientService genrePatientService;
	
	@Autowired
	IPatientRepository patientRepository ;
	
	public static void main(String[] args) {
		SpringApplication.run(MicroservicePatientsApplication.class, args);
	}
	
	@Override	
	@Transactional
	public void run(String... args) throws Exception {
		Patient patient = patientRepository.findById(1).get();
	   Genre listPatientFindByGenre =  genrePatientService.getGenreByGenreId("M");
		System.out.println( "*******************LIST PATIENT BY GENRE**********************"+listPatientFindByGenre);
	}
}
