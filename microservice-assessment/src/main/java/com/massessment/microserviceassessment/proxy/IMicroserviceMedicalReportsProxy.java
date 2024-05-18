package com.massessment.microserviceassessment.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.massessment.microserviceassessment.beans.MedicalReportBean;

@FeignClient(name = "microservice-API-Gateway")
public interface IMicroserviceMedicalReportsProxy {
	@GetMapping("/MICROSERVICE-MEDICALREPORTS/rapport-medical/{patId}")
	public List<MedicalReportBean> getPatientByPatId(@PathVariable Integer patId);
}
