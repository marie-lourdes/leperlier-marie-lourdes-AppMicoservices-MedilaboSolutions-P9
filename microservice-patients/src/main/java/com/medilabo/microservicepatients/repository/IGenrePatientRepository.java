package com.medilabo.microservicepatients.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilabo.microservicepatients.model.Genre;

public interface IGenrePatientRepository extends JpaRepository<Genre, Integer> {
	Genre findGenreByGenreId(Integer genreId);
}