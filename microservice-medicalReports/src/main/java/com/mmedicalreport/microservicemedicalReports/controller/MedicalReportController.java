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
		PatientBean patientFoundByName = new PatientBean();
		//Integer  medicalReportPatId =medicalReportService.getMedicalReportByPatId(patId)("1").get().getPatId();
		//patientFoundByName = microservicePatientsProxy.getPatientByName(medicalReport.getPatient());
		//Integer patientId =  patientFoundByName.getId();
		//String patientName =  patientFoundByName.getNom();
		try {

			log.debug("MedicalReport added: {}", medicalReportCreated);
			//medicalReportCreated.setPatId(patientId);
			//medicalReportCreated.setPatient(patientName);
			//medicalReportCreated.setNote( medicalReport.getNote());
			medicalReportCreated = medicalReportService.addMedicalReport(medicalReport);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}
		return medicalReportCreated;
	}

	@GetMapping("/rapport-medical-byPatient/{namePatient}")
	public MedicalReport getMedicalReportByPatient(@PathVariable String namePatient) {
		MedicalReport medicalReportFoundByPatient = new MedicalReport();

		try {
			medicalReportFoundByPatient = medicalReportService.getMedicalReportByNamePatient(namePatient);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
			// throw new PatientNotFoundException("Patient not found for id " + id);
		}
		return medicalReportFoundByPatient;
	}

	@GetMapping("/rapport-medical-byPatId/{patId}")
	public MedicalReport getPatientByPatId(@PathVariable String patId) {
		MedicalReport medicalReportFoundByPatient = new MedicalReport();
		try {
			medicalReportFoundByPatient = medicalReportService.getMedicalReportByPatId(patId);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
			// throw new PatientNotFoundException("Patient not found for id " + id);
		}
		return medicalReportFoundByPatient;
	}

	/*@GetMapping("/rapport-medical-byId/{id}")
	public MedicalReport getPatientByPatient(@PathVariable String id) {
		MedicalReport medicalReportFoundByPatient = new MedicalReport();
		try {
			medicalReportFoundByPatient = medicalReportService.getMedicalReportById(id);

		} catch (NullPointerException e) {
			log.error(e.getMessage());
			// throw new PatientNotFoundException("Patient not found for id " + id);
		}
		return medicalReportFoundByPatient;
	}*/
}
