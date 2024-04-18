package com.medilabo.microservicepatients.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="prenom")
	private String firstName;
	
	@Column(name="nom")
	private String lastName;
	
	@Column(name="date_de_naissance")
	private Date dateDeNaissance;
	
	@Column(name="genre")
	private String genre;
	
	@Column(name="adresse")
	private String adresse;
	
	@Column(name="telephone")
	private String telephone;
	
}
