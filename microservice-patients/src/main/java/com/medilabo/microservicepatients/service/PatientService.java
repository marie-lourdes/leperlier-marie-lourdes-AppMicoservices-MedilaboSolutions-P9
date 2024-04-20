package com.medilabo.microservicepatients.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

		if (isPatientDuplicated(patientCreated)) {
			throw new IllegalArgumentException(
					"Failed to add this patient, this person already exist" + patientCreated);
		} else {
			log.debug("Patient added: {}", patientCreated);
			return patientRepository.save(patientCreated);
		}
	}

	public Patient updatePatient(Patient patientUpdated, long id) {
		log.debug("Adding patient: {} {}", patientUpdated.getPrenom(), patientUpdated.getNom());

		Patient existingPatientUpdated = new Patient();
		existingPatientUpdated = patientRepository.findAll().stream().filter(patient -> patient.getId() == id)
				.findFirst().map(existingPatient -> {
					existingPatient.setNom(patientUpdated.getNom());
					existingPatient.setPrenom(patientUpdated.getPrenom());
					existingPatient.setDateDeNaissance(patientUpdated.getDateDeNaissance());
					existingPatient.setGenre(patientUpdated.getGenre());
					existingPatient.setAdresse(patientUpdated.getAdresse());
					existingPatient.setTelephone(patientUpdated.getTelephone());
					return existingPatient;
				}).orElseThrow(() -> new NullPointerException("Failed to update patient, :" + patientUpdated));
		patientRepository.save(existingPatientUpdated);

		log.debug("Patient updated: {}", existingPatientUpdated);
		return existingPatientUpdated;
	}

	public Patient getPatientById(long id) {
		log.debug("Retrieving  one patient for id {}", id);

		Patient patientFoundById = new Patient();
		patientFoundById = patientRepository.findById(id)
				.orElseThrow(() -> new NullPointerException("Patient not found, :" + id));
		log.debug("Patient retrieved successfully for id : {}", id);
		return patientFoundById;
	}

	public List<Patient> getAllPatients() throws NullPointerException {
		log.debug("Retrieving  all patients");
		List<Patient> allPatients = new ArrayList<>();
		allPatients = patientRepository.findAll();
		if (allPatients.isEmpty()) {
			throw new NullPointerException("List of patients is empty");

		}
		return allPatients;
	}

	private boolean isPatientDuplicated(Patient patientCreated) {
		Optional<Patient> patientFoundByFirstNameAndLastName = patientRepository
				.findByPrenomAndNom(patientCreated.getPrenom(), patientCreated.getNom());
		if (!patientFoundByFirstNameAndLastName.isEmpty()) {

			boolean isExistingPatientByBirthdate = patientCreated.getDateDeNaissance()
					.equals(patientFoundByFirstNameAndLastName.get().getDateDeNaissance());
			if (isExistingPatientByBirthdate) {
				return true;

			}
		}
		return false;
	}

}
