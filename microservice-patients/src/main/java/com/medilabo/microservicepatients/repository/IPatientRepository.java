package com.medilabo.microservicepatients.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilabo.microservicepatients.model.Patient;

public interface IPatientRepository extends JpaRepository<Patient, Integer>{
  Optional<Patient>findByPrenomAndNom(String prenom, String nom);
}