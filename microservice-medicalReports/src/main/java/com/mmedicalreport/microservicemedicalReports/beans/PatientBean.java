package com.mmedicalreport.microservicemedicalReports.beans;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PatientBean {
	private Integer id;
	private String prenom;
	private String nom;
	private LocalDate dateDeNaissance;
	private String genre;
	private String adresse;
	private String telephone;

	public PatientBean() {

	}
}
