package com.medilabo.microservicepatients.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilabo.microservicepatients.model.Patient;

public interface IPatientRepository extends JpaRepository<Patient,Long>{
	Optional<Patient> addPatient();
    Patient updatePatient();
	Optional<Patient> getPatientById();
	List<Patient> getAllPatients();
}
