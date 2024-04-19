package com.medilabo.microservicepatients.controller;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.microservicepatients.model.Patient;
import com.medilabo.microservicepatients.repository.PatientRepositoryImpl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("patient")
public class PatientController {
	private static final Logger log = LogManager.getLogger(PatientController.class);

	@Autowired
	private PatientRepositoryImpl patientRepositoryImpl;

	@PostMapping("/creation")
	public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient person) {

		Patient personCreated = new Patient();
		try {
			personCreated = patientRepositoryImpl.addPatient(person);

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());

			ResponseEntity<Patient> responseEntityNoValid = this.returnResponseEntityEmptyAndCode400();
			log.error(e.getMessage());
			return responseEntityNoValid;
		}

		ResponseEntity<Patient> responseEntityValid = ResponseEntity.status(HttpStatus.CREATED).body(personCreated);
		log.info("Patient added successfully {}", responseEntityValid);
		return responseEntityValid;
	}

	@PutMapping("/modification")
	public ResponseEntity<Patient> updateOnePatientById(@Valid @RequestBody Patient person, @RequestParam long id) {

		Patient personFoundById = new Patient();
		try {
			personFoundById = patientRepositoryImpl.updatePatient(personFoundById, id);
		} catch (NullPointerException e) {
			log.error(e.getMessage());

			ResponseEntity<Patient> responseEntityNoValid = this.returnResponseEntityEmptyAndCode404();
			return responseEntityNoValid;
		}

		ResponseEntity<Patient> responseEntityValid = ResponseEntity.status(HttpStatus.OK).body(personFoundById);
		log.info("Patient updated successfully", responseEntityValid);
		return ResponseEntity.status(HttpStatus.OK).body(personFoundById);
	}

	private ResponseEntity<Patient> returnResponseEntityEmptyAndCode404() {
		return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<Patient> returnResponseEntityEmptyAndCode400() {
		return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
	}
}
