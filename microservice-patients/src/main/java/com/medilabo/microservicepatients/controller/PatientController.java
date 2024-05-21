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
				throw new PatientConflictException("Failed to add this patient, this person already exist" + patient);
			}
			patientCreated = patientService.addPatient(patient);

			log.debug("Patient successfully added: {}", patientCreated);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}
		return patientCreated;
	}

	@PutMapping("/modification/{id}")
	public Patient updateOnePatientById(@Valid @RequestBody Patient patientUpdated, @PathVariable Integer id) {
		Patient existingPatientUpdated = new Patient();
		
		try {

			existingPatientUpdated = patientService.getAllPatients().stream()
					.filter(patient -> patient.getId().equals(id)).findFirst().map(existingPatient -> {
						existingPatient.setId(patientUpdated.getId());
						existingPatient.setNom(patientUpdated.getNom());
						existingPatient.setPrenom(patientUpdated.getPrenom());
						existingPatient.setDateDeNaissance(patientUpdated.getDateDeNaissance());
						existingPatient.setGenre(patientUpdated.getGenre());
						existingPatient.setAdresse(patientUpdated.getAdresse());
						existingPatient.setTelephone(patientUpdated.getTelephone());
						return existingPatient;
					}).orElseThrow(() -> new NullPointerException("Failed to update patient, :" + patientUpdated));

			patientService.updatePatient(existingPatientUpdated, id);
			log.info("Patient successfully updated : {}", existingPatientUpdated);
		} catch (NullPointerException e) {
			log.error(e.getMessage());
			throw new PatientNotFoundException("Patient not found to update for id  " + id);
		}

		return existingPatientUpdated;
	}

	@GetMapping("/info-patient/{id}")
	public Patient getPatientById(@PathVariable Integer id) {
		Patient patientFoundById = new Patient();
		
		try {
			patientFoundById = patientService.getPatientById(id);

			log.info("Patient successfully retrieved for id : {}, {}", id, patientFoundById);
			return patientFoundById;
		} catch (NullPointerException e) {
			log.error(e.getMessage());
			throw new PatientNotFoundException("Patient not found for id " + id);
		}
	}

	@GetMapping("/list")
	public List<Patient> getAllPatients() {
		List<Patient> allPatients = new ArrayList<>();
		
		try {
			allPatients = patientService.getAllPatients();

			log.info("List of Patient successfully retrieved : {}", allPatients);
			return allPatients;
		} catch (NullPointerException e) {
			log.error(e.getMessage());
			throw new PatientNotFoundException("List of patients not found");
		}

	}

	private boolean isPatientDuplicated(Patient patientCreated) {

		long hasExistingPatient = patientService.getAllPatients().stream()
				.filter(patient -> patientCreated.getNom().equals(patient.getNom())
						&& patientCreated.getPrenom().equals(patient.getPrenom())
						&& patientCreated.getDateDeNaissance().equals(patient.getDateDeNaissance()))
				.count();

		if (hasExistingPatient > 0) {
			return true;
		}
		return false;
	}
}
