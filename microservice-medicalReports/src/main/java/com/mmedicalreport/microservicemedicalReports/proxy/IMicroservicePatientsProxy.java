package com.mmedicalreport.microservicemedicalReports.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mmedicalreport.microservicemedicalReports.bean.PatientBean;

//@FeignClient(name="microservice-patients", url="localhost:9001")
public interface IMicroservicePatientsProxy {
/*	@GetMapping("/MICROSERVICE-PATIENTS/patient/info-patient/{id}")
	PatientBean getPatientById(@PathVariable Integer id) */;

}
