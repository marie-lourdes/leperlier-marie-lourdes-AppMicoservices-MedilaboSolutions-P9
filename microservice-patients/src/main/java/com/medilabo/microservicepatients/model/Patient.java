package com.medilabo.microservicepatients.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.medilabo.microservicepatients.utils.RegexConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "patient")
@Data
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Column(name = "genre_id")
	private Integer genreId;
	
	@NotBlank
	@Column(name = "prenom")
	private String prenom;

	@NotBlank
	@Column(name = "nom")
	private String nom;

	@NotNull
	@Column(name = "date_de_naissance")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDeNaissance;

	@Column(name = "adresse")
	@Pattern(regexp = RegexConstant.REGEX_ADDRESS)
	private String adresse;

	@Column(name = "telephone")
	@Pattern(regexp = RegexConstant.REGEX_PHONE)
	private String telephone;

	@Override
	public String toString() {
		return "Patient {" + "id=" + id + ",  prenom='" + prenom + '\'' + ", nom='" + nom + '\'' + ", dateDeNaissance='"
				+ dateDeNaissance + '\'' + ",adresse='" + adresse + '\'' + ", telephone="
				+ telephone + '}';
	}
}
