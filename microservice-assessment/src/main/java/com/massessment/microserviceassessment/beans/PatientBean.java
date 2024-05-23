package com.massessment.microserviceassessment.beans;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PatientBean {
	private Integer id;
	private String prenom;
	private String nom;
	private LocalDate dateDeNaissance;
	private Integer genreId;

	public PatientBean() {

	}
}
