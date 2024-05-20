package com.mclient.microserviceclient.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mclient.microserviceclient.bean.MedicalReportBean;

import jakarta.validation.Valid;

@FeignClient(name="microservice-API-Gateway")
public interface IMicroserviceAssessmentDiabetesProxy {
	@GetMapping("MICROSERVICE-ASSESSMENT/patient/assessment-diabetes/{id}")
	 String evaluateRiskDiabetePatientById(@PathVariable Integer id);
}
