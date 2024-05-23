package com.massessment.microserviceassessment.beans;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GenreBean {

	private String genreId;
	private List<PatientBean> patients = new ArrayList<>();
	
	public GenreBean(){}
}
