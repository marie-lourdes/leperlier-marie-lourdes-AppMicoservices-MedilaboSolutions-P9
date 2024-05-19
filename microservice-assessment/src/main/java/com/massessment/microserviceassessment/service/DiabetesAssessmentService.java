package com.massessment.microserviceassessment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.massessment.microserviceassessment.beans.MedicalReportBean;
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

	public DiabetesAssessmentService(ICounter counterTermsMedicalReportNotes, FilterInfoPatientImpl filterInfoPatient) {
		this.microservicePatientsProxy = microservicePatientsProxy;
		this.microserviceMedicalReportsProxy = microserviceMedicalReportsProxy;
		this.counterTermsMedicalReportNotes = new CounterTermsMedicalReportNotesImpl();
		this.filterInfoPatient = new FilterInfoPatientImpl(microservicePatientsProxy, microserviceMedicalReportsProxy);
	}

	public Integer countSymptomFromMedicalReportNotes(Integer id) {
		List<MedicalReportBean> medicalReportsBeans = microserviceMedicalReportsProxy.getMedicalReportsByPatId(id);
		List<List<String>> notes = medicalReportsBeans.stream().map(medicalReport -> medicalReport.getNote())
				.collect(Collectors.toList());
		for (List<String> note : notes) {
			numberOfSymptoms = counterTermsMedicalReportNotes.countSymptomFromMedicalReportNotes(note);
		}
		return numberOfSymptoms;
	}
	
	//countSymptomFromMedicalReportNotes(List<String> notes) 

	/*private PatientBean getPatientBean(Integer id) {
		return microservicePatientsProxy.getPatientById(id);
	}*/

}
