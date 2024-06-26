package com.medilabo.microservicepatients.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "genre")
@Data
public class Genre {

	@Id
	@Column(name = "genre_id")
	private String genreId;

	@OneToMany
	@JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
	private List<Patient> patients = new ArrayList<>();

	@Override
	public String toString() {
		return "Genre {" + "genreId=" + genreId + ","   +" patients='" + patients + '}';
	}
}