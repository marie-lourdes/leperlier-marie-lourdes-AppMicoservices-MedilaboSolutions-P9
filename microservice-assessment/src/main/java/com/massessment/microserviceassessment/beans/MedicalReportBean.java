package com.massessment.microserviceassessment.beans;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MedicalReportBean {
	private String id;
	private Integer patId;
	private String patient;
	private List<String> note = new ArrayList<String>();

	public MedicalReportBean() {

	}

	public void addNotes(String line) {
		this.note.add(line);
	}
}
