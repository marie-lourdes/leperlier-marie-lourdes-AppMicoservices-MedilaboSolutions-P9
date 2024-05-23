package com.medilabo.microservicepatients.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.medilabo.microservicepatients.model.Patient;
import com.medilabo.microservicepatients.repository.IPatientRepository;

@Service
public class PatientService {
	private static final Logger log = LogManager.getLogger(PatientService.class);

	private IPatientRepository patientRepository;

	public PatientService(IPatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public Patient addPatient(Patient patientCreated) {
		log.debug("Adding patient: {} {}", patientCreated.getPrenom(), patientCreated.getNom());

		return patientRepository.save(patientCreated);
	}

	public Patient updatePatient(Patient patientUpdated, Integer id) {
		log.debug("Adding patient: {} {}", patientUpdated.getPrenom(), patientUpdated.getNom());

		patientRepository.save(patientUpdated);

		log.debug("Patient updated: {}", patientUpdated);
		return patientUpdated;
	}

	public Patient getPatientById(Integer id) {
		log.debug("Retrieving  one patient for id {}", id);

		Patient patientFoundById = new Patient();
		patientFoundById = patientRepository.findById(id)
				.orElseThrow(() -> new NullPointerException("Patient by id  not found"));

		log.debug("Patient retrieved successfully for id : {}", id);
		return patientFoundById;
	}

	public List<Patient> getPatientByGenre(Integer genreId) {
		log.debug("Retrieving  one patient by genre {}" + genreId);

		List<Patient> patientsFoundByGenre = new ArrayList<>();
		patientsFoundByGenre = patientRepository.findByGenreId(genreId);
		if (patientsFoundByGenre.isEmpty()) {
			throw new NullPointerException("List of patients found by genre id " + genreId + "not found");
		}

		log.debug("List of patients found by genre id retrieved successfully for genre : {}" + genreId);
		return patientsFoundByGenre;
	}

	public List<Patient> getAllPatients() throws NullPointerException {
		log.debug("Retrieving  all patients");
		List<Patient> allPatients = new ArrayList<>();
		allPatients = patientRepository.findAll();
		return allPatients;
	}
}
