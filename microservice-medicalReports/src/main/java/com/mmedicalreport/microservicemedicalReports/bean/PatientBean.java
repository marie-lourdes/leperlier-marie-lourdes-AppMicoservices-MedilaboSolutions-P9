package com.mmedicalreport.microservicemedicalReports.bean;

import lombok.Data;

@Data
public class PatientBean {
	private Integer id;
	private String prenom;
	private String nom;

	public PatientBean() {

	}

	@Override
	public String toString() {
		return "ProductBean{" + "id=" + id + ",  prenom='" + prenom + '\'' + ", nom='" + nom + '\''
				+ ", dateDeNaissance='" +'}';
	}
}
