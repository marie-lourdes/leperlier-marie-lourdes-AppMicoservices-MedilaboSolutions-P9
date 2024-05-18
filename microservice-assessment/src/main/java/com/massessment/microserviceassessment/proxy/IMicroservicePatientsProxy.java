package com.massessment.microserviceassessment.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.massessment.microserviceassessment.beans.PatientBean;

@FeignClient(name = "microservice-API-Gateway")
public interface IMicroservicePatientsProxy {

	@GetMapping("/MICROSERVICE-PATIENTS/patient/info-patient/{id}")
	PatientBean getPatientById(@PathVariable Integer id);
}
