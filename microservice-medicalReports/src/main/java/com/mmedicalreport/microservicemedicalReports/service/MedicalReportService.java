package com.mmedicalreport.microservicemedicalReports.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mmedicalreport.microservicemedicalReports.model.MedicalReport;
import com.mmedicalreport.microservicemedicalReports.repository.IMedicalReportRepository;

@Service
public class MedicalReportService {
	private static final Logger log = LogManager.getLogger(MedicalReportService.class);

	private IMedicalReportRepository medicalReportRepository;

	public MedicalReportService(IMedicalReportRepository medicalReportRepository) {
		this.medicalReportRepository = medicalReportRepository;
	}

	public MedicalReport addMedicalReport(MedicalReport medicalReportCreated) {
		log.debug("Adding patient's medical report : {} {}", medicalReportCreated.getId(),
				medicalReportCreated.getPatient());
		return medicalReportRepository.save(medicalReportCreated);
	}

	public List<MedicalReport> getMedicalReportByPatId(Integer patId) {
		log.debug("Retrieving  one medical report by patient id{}", patId);

		List<MedicalReport> medicalRecordsFoundByPatId = new ArrayList<>();
		medicalRecordsFoundByPatId = medicalReportRepository.findAll().stream()
				.filter(report -> report.getPatId().equals(patId)).collect(Collectors.toList());

		log.debug("MedicalReport retrieved successfully for : {}", patId);
		return medicalRecordsFoundByPatId;
	}
}
