package com.mclient.microserviceclient.bean;

import com.medilabo.microservicepatients.model.Patient;
import com.medilabo.microservicepatients.utils.MessageErrorValidationCustomized;
import com.medilabo.microservicepatients.utils.RegexConstant;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PatientBean {

	private long id;
	private String prenom;
	private String nom;
	private String dateDeNaissance;
	private String genre;
	private String adresse;
	private String telephone;

	public PatientBean() {

	}

	@Override
	public String toString() {
		return "ProductBean{" + "id=" + id + ",  prenom='" + prenom + '\'' + ", nom='" + nom + '\''
				+ ", dateDeNaissance='" + dateDeNaissance + '\'' + ", genre='" + genre + '\'' + ",adresse='" + adresse
				+ '\'' + ", telephone=" + telephone + '}';
	}
}
