package com.massessment.microserviceassessment.service;

import org.springframework.stereotype.Component;

import com.massessment.microserviceassessment.proxy.IMicroserviceMedicalReportsProxy;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;

import lombok.Data;

@Data
@Component
public class DiabetesAssessmentService {
	private ICounter counterTermsMedicalReportNotes;
	private IFilter filterInfoPatient;
	private IMicroservicePatientsProxy microservicePatientsProxy;
	private IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy;
	private Integer numberOfSymptoms;

/*
	public DiabetesAssessmentService(ICounter counterTermsMedicalReportNotes, FilterInfoPatientImpl filterInfoPatient) {

		this.microservicePatientsProxy = microservicePatientsProxy;
		this.microserviceMedicalReportsProxy = microserviceMedicalReportsProxy;
		this.counterTermsMedicalReportNotes = new CounterTermsMedicalReportNotesImpl();
		this.filterInfoPatient = new FilterInfoPatientImpl(microservicePatientsProxy, microserviceMedicalReportsProxy);

	}*/

	/*private PatientBean getPatientBean(Integer id) {
		return microservicePatientsProxy.getPatientById(id);
	}*/

}
