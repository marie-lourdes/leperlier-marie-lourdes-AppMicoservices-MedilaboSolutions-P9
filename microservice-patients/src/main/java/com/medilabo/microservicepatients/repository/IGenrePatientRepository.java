package com.medilabo.microservicepatients.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilabo.microservicepatients.model.Genre;

public interface IGenrePatientRepository extends JpaRepository<Genre, Integer> {
	List<Genre> findBySex(String genre);
}