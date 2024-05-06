package com.mclient.microserviceclient.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.mclient.microserviceclient.bean.PatientBean;

@FeignClient(name ="microservice-API-Gateway")
public interface IMicroservicePatientsProxy {

	@PostMapping("/MICROSERVICE-PATIENTS/creation")
	public ResponseEntity<PatientBean> createPatient(PatientBean person);
	
	@PutMapping("/MICROSERVICE-PATIENTS/modification")
	public ResponseEntity<PatientBean > updateOnePatientById( PatientBean  patient, long id) ;
	
	@GetMapping("/MICROSERVICE-PATIENTS/info-patient/{id}")
	public ResponseEntity<PatientBean> getPatientById(long id); 
	
	@GetMapping("MICROSERVICE-PATIENTS/list")
	public ResponseEntity<List<PatientBean>> getAllPatients() ;
}
