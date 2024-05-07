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

	public Patient addPatient(Patient patientCreated) throws IllegalArgumentException {
		log.debug("Adding patient: {} {}", patientCreated.getPrenom(), patientCreated.getNom());

		return patientRepository.save(patientCreated);
	}

	public Patient updatePatient(Patient patientUpdated, long id) {
		log.debug("Adding patient: {} {}", patientUpdated.getPrenom(), patientUpdated.getNom());

		patientRepository.save(patientUpdated);

		log.debug("Patient updated: {}", patientUpdated);
		return patientUpdated;
	}

	public Patient getPatientById(Integer id) {
		log.debug("Retrieving  one patient for id {}", id);

		Patient patientFoundById = new Patient();
		patientFoundById = patientRepository.findById(id).orElseGet(null);

		log.debug("Patient retrieved successfully for id : {}", id);
		return patientFoundById;
	}

	public Patient getPatientByFullname(String prenom, String nom) {
		log.debug("Retrieving  one patient by full name {}", prenom + nom);

		Patient patientFoundByFullname = new Patient();
		patientFoundByFullname = patientRepository.findByPrenomAndNom(prenom, nom)
				.orElseThrow(() -> new NullPointerException("Patient not found by full name"));

		log.debug("Patient retrieved successfully for : {}", prenom + nom);
		return patientFoundByFullname;
	}

	public List<Patient> getAllPatients() throws NullPointerException {
		log.debug("Retrieving  all patients");
		List<Patient> allPatients = new ArrayList<>();
		allPatients = patientRepository.findAll();
		return allPatients;
	}
}
