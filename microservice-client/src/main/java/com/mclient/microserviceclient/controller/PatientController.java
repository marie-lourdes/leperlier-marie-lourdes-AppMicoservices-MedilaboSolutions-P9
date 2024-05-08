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

	@PostMapping("/validateFormPatient")
	public String addPatient(@Valid @ModelAttribute PatientBean patientCreated, BindingResult result) {

		try {
			if (result.hasErrors()) {
				return "FormPatient";
			}
			microservicePatientsProxy.createPatient(patientCreated);
			log.info("Patient created sucessfully{} :", patientCreated);

		} catch (Exception e) {
			log.error(e.getMessage());
			return "FormPatient";
		}
		return "Patients";
	}

	@GetMapping("/formPatient")
	public String formPatientPage(Model model) {
		PatientBean patient = new PatientBean();
		// List<PatientBean> patients = microservicePatientsProxy.getAllPatients();

		model.addAttribute("patient", patient);
		log.info(" Patient  form  page successfully retrieved");
		return "FormPatient";
	}

	@PostMapping("/updateFormPatient/{id}")
	public String updatePatient(@Valid @ModelAttribute PatientBean patientUpdated, @PathVariable("id") Integer id,
			BindingResult result) {
		try {
			if (result.hasErrors()) {
				return "UpdateFormPatient";
			}

			microservicePatientsProxy.updateOnePatientById(patientUpdated, id);

			log.info("Patient updated sucessfully{}, id: {}", patientUpdated, id);
			return "Patients";

		} catch (Exception e) {
			log.error(e.getMessage());
			return "UpdateFormPatient";
		}

	}

	@GetMapping("/updateFormPatient/{id}")
	public String updateFormPatientPage(@PathVariable("id") Integer id, Model model) {
		PatientBean patientToUpdate = new PatientBean();
		try {
			patientToUpdate = microservicePatientsProxy.getPatientById(id);
			if (patientToUpdate != null) {
				model.addAttribute("patient", patientToUpdate);
			}

			log.info(" Patient  form update page successfully retrieved");
			return "UpdateFormPatient";
			// return Constants.USER_UPDATE_PAGE;
		} catch (NullPointerException e) {
			log.error(e.getMessage());
			return "404";
			// return Constants.ERROR_404_PAGE;
		}
	}

	@GetMapping("/patients")
	public String listPatientsPage(Model model) {

		List<PatientBean> patients = microservicePatientsProxy.getAllPatients();

		model.addAttribute("patients", patients);

		return "Patients";
	}

	@GetMapping("/info-patient/{id}")
	public String infoPatientPage(@PathVariable Integer id, Model model) {

		PatientBean patient = microservicePatientsProxy.getPatientById(id);

		model.addAttribute("patient", patient);

		return "Info-Patient";
	}
}
