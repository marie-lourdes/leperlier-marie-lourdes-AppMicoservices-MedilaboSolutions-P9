package com.mclient.microserviceclient.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mclient.microserviceclient.bean.PatientBean;
import com.mclient.microserviceclient.proxy.IMicroservicePatientsProxy;

import jakarta.validation.Valid;

@Controller
@RequestMapping("home")
public class PatientController {
	private static final Logger log = LogManager.getLogger(PatientController.class);
	
	@Autowired
	private IMicroservicePatientsProxy microservicePatientsProxy;
	
	@PostMapping("/formPatient")
	public String formPatientPage(@Valid @ModelAttribute PatientBean patient, BindingResult result ) {
      
        try {
			if (result.hasErrors()) {
				return "FormPatient";
			}
	
			microservicePatientsProxy.createPatient(patient);
		
        }catch(Exception e) {
        	log.error(e.getMessage());
        }
        return "patients";
	}
	
	@GetMapping("/formPatient")
	public String formPatientPage(Model model) {
        PatientBean patient = new PatientBean();
		//List<PatientBean> patients = microservicePatientsProxy.getAllPatients();

		model.addAttribute("patient", patient);

		return "FormPatient";
	}

	@GetMapping("/patients")
	public String listPatientsPage(Model model) {

		List<PatientBean> patients = microservicePatientsProxy.getAllPatients();

		model.addAttribute("patients", patients);

		return "Patients";
	}
	
	@GetMapping("/info-patient/{id}")
	public String infoPatientPage( @PathVariable Integer id, Model model) {

		PatientBean patient = microservicePatientsProxy.getPatientById(id);

		model.addAttribute("patient", patient);

		return "Info-Patient";
	}
}
