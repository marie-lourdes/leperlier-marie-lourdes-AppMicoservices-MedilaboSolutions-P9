package com.mclient.microserviceclient.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-API-Gateway")
public interface IMicroserviceAssessmentDiabetesProxy {
	
	@GetMapping("MICROSERVICE-ASSESSMENT/patient/assessment-diabetes/{id}")
	String evaluateRiskDiabetePatientById(@PathVariable Integer id);
}
