package com.medilabo.microservicepatients.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilabo.microservicepatients.model.Genre;

public interface IGenrePatientRepository extends JpaRepository<Genre, Integer> {
	Optional<Genre> findGenreByGenreId(Integer genreId);
}