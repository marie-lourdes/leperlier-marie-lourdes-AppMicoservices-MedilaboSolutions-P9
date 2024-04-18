package com.medilabo.microservicepatients.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medilabo.microservicepatients.model.Patient;

@Component
public class PatientRepositoryImpl {
	@Autowired
	private IPatientRepository PatientRepository;

	public List<Patient> getAllPatients() {
		return PatientRepository.findAll();
	}
	public Patient addPatient(Patient patient) {
		return PatientRepository.save( patient);
	}
}
