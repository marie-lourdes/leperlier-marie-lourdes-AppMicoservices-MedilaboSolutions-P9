package com.mmedicalreport.microservicemedicalReports.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mmedicalreport.microservicemedicalReports.model.MedicalReport;
import com.mmedicalreport.microservicemedicalReports.repository.IMedicalReportRepository;



public class MedicalReportService {
	private static final Logger log = LogManager.getLogger(MedicalReportService.class);

	private IMedicalReportRepository medicalReportRepository;
	
	public  MedicalReportService (IMedicalReportRepository medicalReportRepository) {
		this.medicalReportRepository=medicalReportRepository;
	}
	
	public MedicalReport addMedicalReport(Integer patientId, MedicalReport medicalReportCreated ) throws IllegalArgumentException {
		log.debug("Adding patient's medical report : {} {}",medicalReportCreated.getId(), medicalReportCreated.getPatient());

		return medicalReportRepository.save(medicalReportCreated);
	}
}
