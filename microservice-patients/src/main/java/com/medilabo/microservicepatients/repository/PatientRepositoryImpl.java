package com.medilabo.microservicepatients.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medilabo.microservicepatients.model.Patient;

@Component
public class PatientRepositoryImpl {
	@Autowired
	private IPatientRepository patientRepository;
	
	public Patient addPatient(Patient patient) {
		return patientRepository.save( patient);
	}
	public Patient updatePatient(Patient patientUpdated,Long id) {
		
	
		Patient existingpatientUpdated = patientRepository.findAll().stream()
				.filter(patient -> patient.getId()==id).findFirst()
				.map(existingPatient -> {
					existingPatient.setNom( patientUpdated.getNom());
					existingPatient.setPrenom( patientUpdated.getPrenom());
					existingPatient.setDateDeNaissance( patientUpdated.getPrenom());
					existingPatient.setGenre( patientUpdated.getGenre());
					existingPatient.setAdresse( patientUpdated.getAdresse());
					existingPatient.setTelephone( patientUpdated.getTelephone());
					return existingPatient;
				}).orElseThrow(
						() -> new NullPointerException("Failed to update patient, :"
								+patientUpdated));
	/*	if(!patientRepository.findAll().contains(patient.getId())) {
			patientUpdated= patientRepository.save( patient);
		}*/
		return  existingpatientUpdated;
	}
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}
	
	public Optional<Patient> getOnePatient(Long id) {
		
		return patientRepository.findById(id);
	}
	
}
