package com.medilabo.microservicepatients.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.microservicepatients.model.Patient;
import com.medilabo.microservicepatients.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("patient")
public class PatientController {
	private static final Logger log = LogManager.getLogger(PatientController.class);

	@Autowired
	private PatientService patientRepositoryImpl;

	@PostMapping("/creation")
	public Patient createPatient(@Valid @RequestBody Patient patient) {

		Patient personCreated = new Patient();
		try {
			personCreated = patientRepositoryImpl.addPatient(patient);

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());

			/*ResponseEntity<Patient> responseEntityNoValid = this.returnResponseEntityEmptyAndCode409();
			log.error(e.getMessage());
			return responseEntityNoValid;*/
		}

		/*ResponseEntity<Patient> responseEntityValid = ResponseEntity.status(HttpStatus.CREATED).body(personCreated);
		log.info("Patient added successfully {}", responseEntityValid);*/
		//return responseEntityValid;
		return personCreated;
	}

	@PutMapping("/modification")
	public Patient updateOnePatientById(@Valid @RequestBody Patient patient, @RequestParam long id) {

		Patient personUpdated = new Patient();
		try {
			personUpdated = patientRepositoryImpl.updatePatient(patient, id);
		} catch (NullPointerException e) {
			log.error(e.getMessage());

			//ResponseEntity<Patient> responseEntityNoValid = this.returnResponseEntityEmptyAndCode404();
			//return responseEntityNoValid;
		}

	/*	ResponseEntity<Patient> responseEntityValid = ResponseEntity.status(HttpStatus.OK).body(personUpdated);
		log.info("Patient updated successfully", responseEntityValid);*/
	//	return responseEntityValid;
		return personUpdated;
	}

	@GetMapping("/info-patient/{id}")
	public Patient getPatientById(@PathVariable long id) {

		Patient patientFoundById = new Patient();
		try {
			patientFoundById = patientRepositoryImpl.getPatientById(id);
		} catch (NullPointerException e) {
			log.error(e.getMessage());

			/*ResponseEntity<Patient> responseEntityNoValid = this.returnResponseEntityEmptyAndCode404();
			return responseEntityNoValid;*/
		}

		/*ResponseEntity<Patient> responseEntityValid = ResponseEntity.status(HttpStatus.OK).body(patientFoundById);
		log.info("Patient retrieved successfully", responseEntityValid);*/
		//return responseEntityValid;
		return patientFoundById ;
	}

	@GetMapping("/list")
	public List<Patient> getAllPatients() {

		List<Patient> patientFoundById = new ArrayList<>();
		try {
			patientFoundById = patientRepositoryImpl.getAllPatients();
		} catch (NullPointerException e) {
			log.error(e.getMessage());

		/*	ResponseEntity<List<Patient>> responseEntityNoValid = new ResponseEntity<List<Patient>>(
					HttpStatus.NOT_FOUND);
			return responseEntityNoValid;*/
		}

		/*ResponseEntity<List<Patient>> responseEntityValid = ResponseEntity.status(HttpStatus.OK).body(patientFoundById);
		log.info("List of patients retrieved successfully", responseEntityValid);
		return ResponseEntity.status(HttpStatus.OK).body(patientFoundById);*/
		return patientFoundById ;
	}

	/*private ResponseEntity<Patient> returnResponseEntityEmptyAndCode404() {
		return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<Patient> returnResponseEntityEmptyAndCode409() {
		return new ResponseEntity<Patient>(HttpStatus.CONFLICT);
	}*/
}
