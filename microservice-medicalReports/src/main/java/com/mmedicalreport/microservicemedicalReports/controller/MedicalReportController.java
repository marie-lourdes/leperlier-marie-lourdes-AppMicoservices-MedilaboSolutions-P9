package com.mmedicalreport.microservicemedicalReports.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmedicalreport.microservicemedicalReports.beans.PatientBean;
import com.mmedicalreport.microservicemedicalReports.model.MedicalReport;
import com.mmedicalreport.microservicemedicalReports.proxy.IMicroservicePatientsProxy;
import com.mmedicalreport.microservicemedicalReports.service.MedicalReportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("patient")
public class MedicalReportController {
	private static final Logger log = LogManager.getLogger(MedicalReportController.class);

	@Autowired
	private MedicalReportService medicalReportService;

	@Autowired
	private IMicroservicePatientsProxy microservicePatientsProxy;

	@PostMapping("/creationRapportMedical/{id}")
	public MedicalReport createMedicalReport(@PathVariable Integer id,
			@Valid @RequestBody MedicalReport medicalReport) {
		MedicalReport medicalReportCreated = new MedicalReport();

		try {
			PatientBean PatientFoundById = microservicePatientsProxy.getPatientById(id);
			medicalReportCreated.setPatId(PatientFoundById.getId());
			medicalReportCreated.setPatient(PatientFoundById.getNom());
			medicalReportCreated.setNote(medicalReport.getNote());
			medicalReportService.addMedicalReport(medicalReportCreated);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}

		log.info("Medical report sucessfully created: {}", medicalReportCreated);
		return medicalReportCreated;
	}

	@GetMapping("/rapport-medical/{id}")
	public List<MedicalReport> getMedicalReportsByPatId(@PathVariable Integer id) {
		List<MedicalReport> medicalReportFoundByPatId = new ArrayList<>();

		try {
			medicalReportFoundByPatId = medicalReportService
					.getMedicalReportByPatId(microservicePatientsProxy.getPatientById(id).getId());
		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}

		log.info("Medical report sucessfully retrieved for patient id: {}, {}", id, medicalReportFoundByPatId);
		return medicalReportFoundByPatId;
	}
}
