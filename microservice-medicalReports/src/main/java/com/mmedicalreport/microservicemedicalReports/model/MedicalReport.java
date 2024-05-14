package com.mmedicalreport.microservicemedicalReports.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="medicalreports")
public class MedicalReport { 
	@Id
    private String id;	
    private Integer patId;
    private String patient;
    private List<String> note= new ArrayList<>();  
}
