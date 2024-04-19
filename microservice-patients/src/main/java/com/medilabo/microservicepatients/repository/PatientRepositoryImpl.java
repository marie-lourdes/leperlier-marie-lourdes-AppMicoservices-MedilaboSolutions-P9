package com.medilabo.microservicepatients.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medilabo.microservicepatients.model.Patient;

@Component
public class PatientRepositoryImpl {
	private static final Logger log = LogManager.getLogger(PatientRepositoryImpl.class);
	@Autowired
	private IPatientRepository patientRepository;
	
	public Patient addPatient(Patient patientCreated) throws IllegalArgumentException{
		log.debug("Adding patient: {} {}", patientCreated.getPrenom(), patientCreated.getNom());
		
		boolean isExistingPatient = patientRepository.findAll().stream().filter(patient -> patient.getId()==patientCreated.getId()).collect(Collectors.toList()).isEmpty();
		if(!isExistingPatient) {
			throw new IllegalArgumentException("Failed to add this patient, this person already exist" + patientCreated);
		}else {
			patientRepository.save( patientCreated);
			log.debug("Patient added: {}", patientCreated);
		}
		
		return patientRepository.save( patientCreated);
	}
	
	public Patient updatePatient(Patient patientUpdated,long id) {
		log.debug("Adding patient: {} {}",  patientUpdated.getPrenom(),  patientUpdated.getNom());
		
		Patient existingPatientUpdated= new Patient();
		existingPatientUpdated = patientRepository.findAll().stream()
				.filter(patient -> patient.getId()==id).findFirst()
				.map(existingPatient -> {
					existingPatient.setNom( patientUpdated.getNom());
					existingPatient.setPrenom( patientUpdated.getPrenom());
					existingPatient.setDateDeNaissance( patientUpdated.getDateDeNaissance());
					existingPatient.setGenre( patientUpdated.getGenre());
					existingPatient.setAdresse( patientUpdated.getAdresse());
					existingPatient.setTelephone( patientUpdated.getTelephone());
					return existingPatient;
				}).orElseThrow(
						() -> new NullPointerException("Failed to update patient, :"
								+patientUpdated));
		 patientRepository.save(existingPatientUpdated);
		 
		 log.debug("Patient updated: {}", existingPatientUpdated);
		return  existingPatientUpdated;
	}

	public Patient getPatientById(long id) {
		log.debug("Retrieving  one patient for id {}", id);

		Patient patientFoundById = new Patient();
		patientFoundById = patientRepository.findById(id).orElseThrow(()->new NullPointerException("Patient not found, :"
				+id));	
		log.debug("Patient retrieved successfully for id : {}", id);
		return  patientFoundById ;
	}
	
	public List<Patient> getAllPatients() throws NullPointerException{
		log.debug("Retrieving  all patients");
		List<Patient> allPatients = new ArrayList<>();
		allPatients = patientRepository.findAll();
		if(allPatients.isEmpty()) {
			throw new NullPointerException("List of patients is empty");
	
		}
	return allPatients;
	}
	
}
