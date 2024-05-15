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
				.filter(report -> report.getPatId() == patId).collect(Collectors.toList());

		if (medicalRecordsFoundByPatId.isEmpty()) {
			throw new NullPointerException("MedicalReport not found by patient id :" + patId);
		}

		log.debug("MedicalReport retrieved successfully for : {}", patId);
		return medicalRecordsFoundByPatId;
	}
	
	/*public MedicalReport getMedicalReportByNamePatient(String namePatient) {
	log.debug("Retrieving  one medical report by name patient {}", namePatient);

	MedicalReport medicalReportFoundByPatient = new MedicalReport();
	medicalReportFoundByPatient= medicalReportRepository.findByPatient(namePatient)
			.orElseThrow(() -> new NullPointerException("MedicalReport not found by full name"));

	log.debug("MedicalReport retrieved successfully for : {}", namePatient);
	return medicalReportFoundByPatient;
     }*/
	/*public MedicalReport getMedicalReportById(String id) {
		log.debug("Retrieving  one medical report by id{}", id);

		MedicalReport medicalReportFoundById = new MedicalReport();
		medicalReportFoundById = medicalReportRepository.findById(id)
				.orElseThrow(() -> new NullPointerException("MedicalReport not found by id"));

		log.debug("MedicalReport retrieved successfully for : {}", id);
		return medicalReportFoundById;
	}*/

	public List<MedicalReport> getAllMedicalReports() throws NullPointerException {
		log.debug("Retrieving  all medical reports");
		List<MedicalReport> allMedicalReports = new ArrayList<>();
		allMedicalReports = medicalReportRepository.findAll();
		return allMedicalReports;
	}
}
