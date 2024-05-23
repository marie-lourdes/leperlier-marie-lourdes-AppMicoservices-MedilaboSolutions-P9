package com.medilabo.microservicepatients.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="genre")
@Data
public class Genre {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "genre")
	private String genre;
	
	@OneToMany(
			   cascade = CascadeType.ALL, 
			   orphanRemoval = true, 
			   fetch = FetchType.EAGER)
				@JoinColumn(name = "id")
	private List<Patient> patients= new ArrayList<>();

}
