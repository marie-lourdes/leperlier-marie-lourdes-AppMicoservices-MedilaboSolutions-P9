package com.mmedicalreport.microservicemedicalReports.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mmedicalreport.microservicemedicalReports.repository.IMedicalReportRepository;



public class MedicalReportService {
	private static final Logger log = LogManager.getLogger(MedicalReportService.class);

	private IMedicalReportRepository medicalReportRepository;
}
