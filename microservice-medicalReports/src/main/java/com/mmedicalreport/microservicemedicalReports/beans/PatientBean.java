package com.mmedicalreport.microservicemedicalreports.beans;

import lombok.Data;

@Data
public class PatientBean {
	private Integer id;
	private String nom;

	public PatientBean() {

	}
}
