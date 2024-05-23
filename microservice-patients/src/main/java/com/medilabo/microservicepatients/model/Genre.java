package com.medilabo.microservicepatients.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="genre")
public class Genre {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "genre")
	private String genre;
	
	private List<Patient> patients= new ArrayList<>();

}
