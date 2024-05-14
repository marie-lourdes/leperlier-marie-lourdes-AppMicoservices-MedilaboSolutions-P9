package com.mmedicalreport.microservicemedicalReports.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmedicalreport.microservicemedicalReports.bean.PatientBean;
import com.mmedicalreport.microservicemedicalReports.model.MedicalReport;
import com.mmedicalreport.microservicemedicalReports.proxy.IMicroservicePatientsProxy;
import com.mmedicalreport.microservicemedicalReports.repository.IMedicalReportRepository;
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

	@PostMapping("/creationRapportMedical")
	public MedicalReport createMedicalReport(@Valid @RequestBody MedicalReport medicalReport) {
		MedicalReport medicalReportCreated = new MedicalReport();
		PatientBean patientFoundById = new PatientBean();
		patientFoundById = microservicePatientsProxy.getPatientById(medicalReport.getPatId());
		Integer patientId = patientFoundById.getId();
		String patientName = patientFoundById.getNom();
		try {

			log.debug("MedicalReport added: {}", medicalReportCreated);
			medicalReport.setPatId(patientId);
			medicalReport.setPatient(patientName);
			medicalReportCreated = medicalReportService.addMedicalReport(medicalReport);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}
		return medicalReportCreated;
	}

	@GetMapping("/rapport-medical/{namePatient}")
	public MedicalReport getMedicalReportByPatient(@PathVariable String namePatient) {
		MedicalReport medicalReportFoundByPatient = new MedicalReport();

		try {
			medicalReportFoundByPatient =   medicalReportService.getMedicalReportByNamePatient(namePatient);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
			log.error("medical report  not found for name patient " + namePatient);
			
			// throw new PatientNotFoundException("Patient not found for id " + id);
		}
		return medicalReportFoundByPatient;
	}
}
