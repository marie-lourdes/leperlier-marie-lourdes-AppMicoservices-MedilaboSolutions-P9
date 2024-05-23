package com.medilabo.microservicepatients.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medilabo.microservicepatients.model.Genre;
import com.medilabo.microservicepatients.repository.IGenrePatientRepository;

import jakarta.transaction.Transactional;

@Service
public class GenrePatientService {
	private static final Logger log = LogManager.getLogger(GenrePatientService.class);

	@Autowired
	private IGenrePatientRepository genrePatientRepository;

	public GenrePatientService(IGenrePatientRepository genrePatientRepository) {
		this.genrePatientRepository = genrePatientRepository;
	}

	@Transactional
	public Genre getGenreByGenreId(String genderId) {
		log.debug("Retrieving  one genre  patient by genreId {}" + genderId);

		Genre genreFoundById = new Genre();
		genreFoundById = genrePatientRepository.findGenreByGenreId(genderId)
				.orElseThrow(() -> new NullPointerException("Genre by id  not found: " + genderId));

		log.debug("Genre found by id retrieved successfully for genre : {}" + genderId);
		return genreFoundById;
	}
}
