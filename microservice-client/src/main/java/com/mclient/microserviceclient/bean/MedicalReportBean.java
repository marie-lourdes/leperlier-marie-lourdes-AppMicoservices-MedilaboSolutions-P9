package com.mclient.microserviceclient.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MedicalReportBean {
	private String id;
	private Integer patId;
	private String patient;
	private List<String> note = new ArrayList<>();

	public MedicalReportBean() {

	}
}
