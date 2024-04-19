package com.medilabo.microservicepatients.model;

import com.medilabo.microservicepatients.utils.RegexConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "patient")
@Data
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Column(name = "prenom")
	private String prenom;

	@NotBlank
	@Column(name = "nom")
	private String nom;

	@Column(name = "date_de_naissance")
	@Pattern(regexp = RegexConstant.REGEX_DATE, message = "la date de naissance doit avoir le format suivant: YYYY-MM-DD")
	private String dateDeNaissance;

	@Column(name = "genre")
	@Pattern(regexp = RegexConstant.REGEX_GENRE, message = "Le genre doit indiqué avec 'F' pour féminin et 'M' pour  masculin et en majuscule ")
	private String genre;

	@Column(name = "adresse")
	@Pattern(regexp = RegexConstant.REGEX_ADDRESS, message = "l'adresse doit avoir le format suivant: 000 nom de la voie, rue")
	private String adresse;

	@Column(name = "telephone")
	@Pattern(regexp = RegexConstant.REGEX_PHONE, message = "le numéro de téléphone doit avoir le format suivant: 000-000-0000")
	private String telephone;

}
