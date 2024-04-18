package com.medilabo.microservicepatients.model;

import com.medilabo.microservicepatients.utils.RegexConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name="patient")
@Data
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="date_de_naissance")
	@Pattern(regexp = RegexConstant.REGEX_DATE)
	private String dateDeNaissance;
	
	@Column(name="genre")
	@Pattern(regexp = RegexConstant.REGEX_GENRE)
	private String genre;
	
	@Column(name="adresse")
	@Pattern(regexp = RegexConstant.REGEX_ADDRESS)
	private String adresse;
	
	@Column(name="telephone")
	@Pattern(regexp = RegexConstant.REGEX_PHONE)
	private String telephone;
	
}
