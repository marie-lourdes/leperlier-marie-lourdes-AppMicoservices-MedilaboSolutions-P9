package com.mclient.microserviceclient.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mclient.microserviceclient.bean.MedicalReportBean;

import jakarta.validation.Valid;

@FeignClient(name = "microservice-API-Gateway")
public interface IMicroserviceMedicalReportsProxy {
	
	@GetMapping("/MICROSERVICE-MEDICALREPORTS/patient/rapport-medical/{patId}")
	List<MedicalReportBean> getMedicalReportsByPatId(@PathVariable Integer patId);

	@PostMapping("/MICROSERVICE-MEDICALREPORTS/patient/creationRapportMedical/{id}")
	public MedicalReportBean createMedicalReport(@PathVariable Integer id,
			@Valid @RequestBody MedicalReportBean medicalReport);
}
