package com.medilabo.microservicepatients.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.medilabo.microservicepatients.utils.MessageErrorValidationCustomized;
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

	@NotBlank
	@Column(name = "prenom")
	private String prenom;

	@NotBlank
	@Column(name = "nom")
	private String nom;
	
	@NotNull
	@Column(name = "date_de_naissance")
	//@Pattern(regexp = RegexConstant.REGEX_DATE, message = MessageErrorValidationCustomized.ERROR_MSG_BIRTHDATE_PATTERN)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDeNaissance;

	@NotBlank
	@Column(name = "genre")
	@Pattern(regexp = RegexConstant.REGEX_GENDER, message = MessageErrorValidationCustomized.ERROR_MSG_GENDER_PATTERN)
	private String genre;

	@Column(name = "adresse")
	@Pattern(regexp = RegexConstant.REGEX_ADDRESS, message = MessageErrorValidationCustomized.ERROR_MSG_ADDRESS_PATTERN)
	private String adresse;

	@Column(name = "telephone")
	@Pattern(regexp = RegexConstant.REGEX_PHONE, message = MessageErrorValidationCustomized.ERROR_MSG_PHONE_PATTERN)
	private String telephone;

	@Override
	public String toString() {
		return "ProductBean{" + "id=" + id + ",  prenom='" + prenom + '\'' + ", nom='" + nom + '\''
				+ ", dateDeNaissance='" + dateDeNaissance + '\'' + ", genre='" + genre + '\'' + ",adresse='" + adresse
				+ '\'' + ", telephone=" + telephone + '}';
	}
}
