package com.mclient.microserviceclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mclient.microserviceclient.bean.PatientBean;
import com.mclient.microserviceclient.proxy.IMicroservicePatientsProxy;

@Controller
@RequestMapping("home")
public class PatientController {
	@Autowired
	private IMicroservicePatientsProxy microservicePatientsProxy;

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
