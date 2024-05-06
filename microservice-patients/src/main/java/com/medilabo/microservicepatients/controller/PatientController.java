package com.medilabo.microservicepatients.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.microservicepatients.exceptions.PatientConflictException;
import com.medilabo.microservicepatients.exceptions.PatientNotFoundException;
import com.medilabo.microservicepatients.model.Patient;
import com.medilabo.microservicepatients.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("patient")
public class PatientController {
	private static final Logger log = LogManager.getLogger(PatientController.class);

	@Autowired
	private PatientService patientService;

	@PostMapping("/creation")
	public Patient createPatient(@Valid @RequestBody Patient patient) {
		Patient patientCreated = new Patient();
				
		try {
			if (isPatientDuplicated(patient)) {
				return null;		
			}			
			log.debug("Patient added: {}", patientCreated);
			patientCreated= patientService.addPatient(patient);
			
		} catch (NullPointerException e) {
			log.error(e.getMessage());
			 throw new PatientConflictException("Failed to add this patient, this person already exist" + patientCreated); 

			/*ResponseEntity<Patient> responseEntityNoValid = this.returnResponseEntityEmptyAndCode409();
			log.error(e.getMessage());
			return responseEntityNoValid;*/
		}

		/*ResponseEntity<Patient> responseEntityValid = ResponseEntity.status(HttpStatus.CREATED).body(personCreated);
		log.info("Patient added successfully {}", responseEntityValid);*/
		//return responseEntityValid;
		return patientCreated;
	}

	@PutMapping("/modification")
	public Patient updateOnePatientById(@Valid @RequestBody Patient patientUpdated, @RequestParam long id) {
		Patient existingPatientUpdated = new Patient();
		try {
		//Patient patientUpdated = new Patient();
		
	
		existingPatientUpdated = patientService.getAllPatients().stream().filter(patient -> patient.getId() == id)
				.findFirst().map(existingPatient -> {
					existingPatient.setNom(patientUpdated.getNom());
					existingPatient.setPrenom(patientUpdated.getPrenom());
					existingPatient.setDateDeNaissance(patientUpdated.getDateDeNaissance());
					existingPatient.setGenre(patientUpdated.getGenre());
					existingPatient.setAdresse(patientUpdated.getAdresse());
					existingPatient.setTelephone(patientUpdated.getTelephone());
					return existingPatient;
				}).orElseThrow(() -> new NullPointerException("Failed to update patient, :" + patientUpdated));
		
		
		 patientService.updatePatient(existingPatientUpdated, id);
		} catch (NullPointerException e) {
			log.error(e.getMessage());
			  throw new PatientNotFoundException("Patient not found "+id);
			//ResponseEntity<Patient> responseEntityNoValid = this.returnResponseEntityEmptyAndCode404();
			//return responseEntityNoValid;
		}

	/*	ResponseEntity<Patient> responseEntityValid = ResponseEntity.status(HttpStatus.OK).body(patientUpdated);
		log.info("Patient updated successfully", responseEntityValid);*/
	//	return responseEntityValid;
		return existingPatientUpdated ;
	}

	@GetMapping("/info-patient/{id}")
	public Patient getPatientById(@PathVariable long id) {

		Patient patientFoundById = new Patient();
		try {
			patientFoundById = patientService.getPatientById(id);
			
		} catch (NullPointerException e) {
			log.error(e.getMessage());
			  throw new PatientNotFoundException("Patient not found for id "+id);
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
			patientFoundById = patientService.getAllPatients();
		} catch (NullPointerException e) {
			log.error(e.getMessage());
		//	  throw new PatientNotFoundException("Patient not found for id "+id);

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
	private boolean isPatientDuplicated(Patient patientCreated) throws NullPointerException{
		Patient patientFoundByFirstNameAndLastName = patientService
				.getPatientByFullname(patientCreated.getPrenom(), patientCreated.getNom());
		if (!(patientFoundByFirstNameAndLastName == null)) {

			boolean isExistingPatientByBirthdate = patientCreated.getDateDeNaissance()
					.equals(patientFoundByFirstNameAndLastName.getDateDeNaissance());
			if (isExistingPatientByBirthdate) {
				return true;

			}
		}
		return false;
	}
	}
