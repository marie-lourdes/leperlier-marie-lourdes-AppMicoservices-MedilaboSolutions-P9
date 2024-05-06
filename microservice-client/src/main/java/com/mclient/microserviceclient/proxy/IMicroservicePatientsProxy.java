package com.mclient.microserviceclient.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mclient.microserviceclient.bean.PatientBean;

import jakarta.validation.Valid;

@FeignClient(name = "microservice-API-Gateway")
public interface IMicroservicePatientsProxy {

	@PostMapping("/MICROSERVICE-PATIENTS/patient/creation")
	PatientBean createPatient(@Valid @RequestBody PatientBean patient);

	@PutMapping("/MICROSERVICE-PATIENTS/patient/modification")
	PatientBean updateOnePatientById(@Valid @RequestBody PatientBean patient, @RequestParam long id);

	@GetMapping("/MICROSERVICE-PATIENTS/patient/info-patient/{id}")
	PatientBean getPatientById(@PathVariable long id) ;

	@GetMapping("MICROSERVICE-PATIENTS/patient/list")
	List<PatientBean> getAllPatients() ;
}
