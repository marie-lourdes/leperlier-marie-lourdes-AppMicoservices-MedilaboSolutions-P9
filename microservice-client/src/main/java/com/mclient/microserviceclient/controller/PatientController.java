package com.mclient.microserviceclient.controller;

import java.util.ArrayList;
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

import com.mclient.microserviceclient.bean.MedicalReportBean;
import com.mclient.microserviceclient.bean.PatientBean;
import com.mclient.microserviceclient.proxy.IMicroserviceAssessmentDiabetesProxy;
import com.mclient.microserviceclient.proxy.IMicroserviceMedicalReportsProxy;
import com.mclient.microserviceclient.proxy.IMicroservicePatientsProxy;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/home")
public class PatientController {
	private static final Logger log = LogManager.getLogger(PatientController.class);

	@Autowired
	private IMicroservicePatientsProxy microservicePatientsProxy;

	@Autowired
	private IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy;

	@Autowired
	private IMicroserviceAssessmentDiabetesProxy microserviceAssessmentDiabetesProxy;

	@PostMapping("/validateFormPatient")
	public String addPatient(@Valid @ModelAttribute PatientBean patientCreated, BindingResult result) {

		try {
			if (result.hasErrors()) {
				return "FormPatient";
			}
			patientCreated = microservicePatientsProxy.createPatient(patientCreated);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}
		log.info("Patient created sucessfully{} :", patientCreated);
		return "redirect:/home/all-patients";
	}

	@GetMapping("/formPatient")
	public String formPatientPage(Model model) {
		PatientBean patient = new PatientBean();

		model.addAttribute("patient", patient);

		log.info(" Patient  form  page successfully retrieved");
		return "FormPatient";
	}

	@PostMapping("/updateFormPatient/{id}")
	public String updatePatient(@PathVariable Integer id, @Valid @ModelAttribute PatientBean patientUpdated,
			BindingResult result) {
		try {
			if (result.hasErrors()) {
				return "UpdateFormPatient";
			}
			microservicePatientsProxy.updateOnePatientById(patientUpdated, id);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}

		log.info("Patient updated sucessfully{}, id: {}", patientUpdated, id);
		return "redirect:/home/all-patients";
	}

	@GetMapping("/updateFormPatient/{id}")
	public String updateFormPatientPage(@PathVariable Integer id, Model model) {
		PatientBean patientToUpdate = new PatientBean();

		try {
			patientToUpdate = microservicePatientsProxy.getPatientById(id);
			if (patientToUpdate != null) {
				model.addAttribute("patient", patientToUpdate);
			}
		} catch (NullPointerException e) {
			log.error(e.getMessage());
			return "UpdateFormPatient";
		}

		log.info(" Patient  form update page successfully retrieved");
		return "UpdateFormPatient";
	}

	@PostMapping("/validateFormMedicalReport/{id}")
	public String addMedicalReport(@PathVariable Integer id,
			@Valid @ModelAttribute MedicalReportBean medicalReportCreated, BindingResult result) {

		try {
			if (result.hasErrors()) {
				return "FormMedicalReport";
			}
			PatientBean patientToCreate = microservicePatientsProxy.getPatientById(id);
			medicalReportCreated = microserviceMedicalReportsProxy.createMedicalReport(patientToCreate.getId(),
					medicalReportCreated);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}

		log.info("medicalReport created sucessfully{} :", medicalReportCreated);
		return "redirect:/home/medicalReport/{id}";
	}

	@GetMapping("/formMedicalReport/{id}")
	public String formMedicalReportPage(@PathVariable Integer id, Model model) {
		MedicalReportBean medicalReport = new MedicalReportBean();
		PatientBean patientFoundById = new PatientBean();

		patientFoundById = microservicePatientsProxy.getPatientById(id);
		medicalReport.setPatId(patientFoundById.getId());
		medicalReport.setPatient(patientFoundById.getNom());
		model.addAttribute("medicalReport", medicalReport);

		log.info(" MedicalReport  form  page successfully retrieved");
		return "FormMedicalReport";
	}

	@GetMapping("/medicalReport/{id}")
	public String reportMedicalPatientPage(@PathVariable Integer id, Model model) {
		PatientBean patientFoundById = new PatientBean();
		List<MedicalReportBean> medicalReportsFoundByPatientId = new ArrayList<>();
		String riskDiabeteEvaluated = null;

		try {
			patientFoundById = microservicePatientsProxy.getPatientById(id);
			medicalReportsFoundByPatientId = microserviceMedicalReportsProxy.getMedicalReportsByPatId(id);
			riskDiabeteEvaluated = microserviceAssessmentDiabetesProxy.evaluateRiskDiabetePatientById(id);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}
		model.addAttribute("patient", patientFoundById);

		if (medicalReportsFoundByPatientId != null) {
			model.addAttribute("medicalReports", medicalReportsFoundByPatientId);
		}

		model.addAttribute("riskDiabeteEvaluated", riskDiabeteEvaluated);
		return "Info-Patient";
	}

	@GetMapping("/all-patients")
	public String listPatientsPage(Model model) {
		List<PatientBean> patients = new ArrayList<>();

		try {
			patients = microservicePatientsProxy.getAllPatients();

		} catch (NullPointerException e) {
			log.error(e.getMessage());
			return "error-404";
		}
		model.addAttribute("patients", patients);
		return "Patients";
	}
}
