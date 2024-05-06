package com.mclient.microserviceclient.bean;

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
