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

import com.mmedicalreport.microservicemedicalReports.exceptions.ReportMedicalNotFoundException;
import com.mmedicalreport.microservicemedicalReports.model.MedicalReport;
import com.mmedicalreport.microservicemedicalReports.service.MedicalReportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("patient")
public class MedicalReportController {
	private static final Logger log = LogManager.getLogger(MedicalReportController.class);

	@Autowired
	private MedicalReportService medicalReportService;

	@PostMapping("/creationRapportMedical/{id}")
	public MedicalReport createMedicalReport(@PathVariable Integer id,
			@Valid @RequestBody MedicalReport medicalReport) {
		MedicalReport medicalReportCreated = new MedicalReport();
		try {
			medicalReportCreated.setPatId(id);
			medicalReportCreated.setPatient(medicalReport.getPatient());
			medicalReportCreated.setNote(medicalReport.getNote());
			medicalReportCreated = medicalReportService.addMedicalReport(medicalReportCreated);
			log.info("Medical report sucessfully created: {}", medicalReportCreated);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return medicalReportCreated;
	}

	@GetMapping("/rapport-medical/{patId}")
	public List<MedicalReport> getPatientByPatId(@PathVariable Integer patId) {
		List<MedicalReport> medicalReportFoundByPatId = new ArrayList<>();
		try {
			medicalReportFoundByPatId = medicalReportService.getMedicalReportByPatId(patId);

			log.info("Medical report sucessfully retrieved for patient id: {}, {}", patId, medicalReportFoundByPatId);
			return medicalReportFoundByPatId;
		} catch (NullPointerException e) {
			log.error(e.getMessage());
			throw new ReportMedicalNotFoundException(" ReportMedical not found for id " + patId);
		}

	}

	/*
	 * @GetMapping("/rapport-medical-byPatient/{namePatient}") public MedicalReport
	 * getMedicalReportByPatient(@PathVariable String namePatient) { MedicalReport
	 * medicalReportFoundByPatient = new MedicalReport();
	 * 
	 * try { medicalReportFoundByPatient =
	 * medicalReportService.getMedicalReportByNamePatient(namePatient);
	 * 
	 * } catch (NullPointerException e) { log.error(e.getMessage()); // throw new
	 * PatientNotFoundException("Patient not found for id " + id); } return
	 * medicalReportFoundByPatient; }
	 * 
	 * @GetMapping("/rapport-medical-byId/{id}") public MedicalReport
	 * getPatientByPatient(@PathVariable String id) { MedicalReport
	 * medicalReportFoundByPatient = new MedicalReport(); try {
	 * medicalReportFoundByPatient = medicalReportService.getMedicalReportById(id);
	 * 
	 * } catch (NullPointerException e) { log.error(e.getMessage()); // throw new
	 * PatientNotFoundException("Patient not found for id " + id); } return
	 * medicalReportFoundByPatient; }
	 */
}
