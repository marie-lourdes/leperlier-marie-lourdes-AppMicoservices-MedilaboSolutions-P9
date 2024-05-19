package com.massessment.microserviceassessment.service;

import java.util.List;
import java.util.stream.Collectors;

import com.massessment.microserviceassessment.beans.MedicalReportBean;
import com.massessment.microserviceassessment.beans.PatientBean;
import com.massessment.microserviceassessment.proxy.IMicroserviceMedicalReportsProxy;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;

public class EvaluatorDiabeteImpl  implements IEvaluatorRiskDiabete{
	private ICounter counterTermsMedicalReportNotes;
	private IFilter filterInfoPatient;
	private IMicroservicePatientsProxy microservicePatientsProxy;
	private IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy;
	private Integer numberOfSymptoms;
	
	public EvaluatorDiabeteImpl (ICounter counterTermsMedicalReportNotes, FilterInfoPatientImpl filterInfoPatient) {
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
	@Override
	public boolean evaluateAsRiskNone(PatientBean patient) {
		return false;
	}
	
	@Override
	public boolean   evaluateAsRiskBorderLine(PatientBean patient) {
		return false;
	}

	@Override
	public boolean   evaluateAsRiskDanger(PatientBean patient) {
		return false;
	}
	
	@Override
	public boolean  evaluateAsRiskEarlyOnSet(PatientBean patient) {
		return false;
	}
}
