package com.massessment.microserviceassessment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.massessment.microserviceassessment.beans.MedicalReportBean;
import com.massessment.microserviceassessment.proxy.IMicroserviceMedicalReportsProxy;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;
import com.massessment.microserviceassessment.utils.ConstantRiskDiabete;

@Component
public class EvaluatorDiabeteImpl implements IEvaluatorRiskDiabete {
	private ICounter counterTermsMedicalReportNotes;
	private IFilter filterInfoPatient;
	private IMicroservicePatientsProxy microservicePatientsProxy;
	private IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy;
	private Integer numberOfSymptoms;

	public EvaluatorDiabeteImpl(IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy,
			ICounter counterTermsMedicalReportNotes) {
		this.microservicePatientsProxy = microservicePatientsProxy;
		this.microserviceMedicalReportsProxy = microserviceMedicalReportsProxy;
		this.filterInfoPatient = new FilterInfoPatientImpl(microservicePatientsProxy);
		this.counterTermsMedicalReportNotes = new CounterTermsMedicalReportNotesImpl();

	}

	public Integer countSymptomFromMedicalReportNotes(Integer id) {
		List<MedicalReportBean> medicalReportsBeans = this.getMedicalReportBean(id);
		List<List<String>> notes = medicalReportsBeans.stream().map(medicalReport -> medicalReport.getNote())
				.collect(Collectors.toList());
		for (List<String> note : notes) {
			numberOfSymptoms = counterTermsMedicalReportNotes.countSymptomFromMedicalReportNotes(note);
		}
		return numberOfSymptoms;
	}

	@Override
	public String evaluateAsRiskNone(Integer id, MedicalReportBean medicalReport) {
		numberOfSymptoms = countSymptomFromMedicalReportNotes(id);
		return numberOfSymptoms == 0 ? ConstantRiskDiabete.RISK_NONE : null;
	}

	@Override
	public String evaluateAsRiskBorderLine(MedicalReportBean medicalReport) {
		return null;
	}

	@Override
	public String evaluateAsRiskDanger(MedicalReportBean medicalReport) {
		return null;
	}

	@Override
	public String evaluateAsRiskEarlyOnSet(MedicalReportBean medicalReport) {
		return null;
	}

	public List<MedicalReportBean> getMedicalReportBean(Integer patientId) {
		return microserviceMedicalReportsProxy.getMedicalReportsByPatId(patientId);
	}
	
	/*public List<List<String>>  getMedicalReportNotes(Integer patientId) {
		List<List<String>> listMedicalReportOfPatient=this.getMedicalReportBean(patientId).stream()
				.map(medicalReport-> medicalReport.getNote())
				.collect(Collectors.toList());
		//listMedicalReportOfPatient.stream().
		System.out.println("-----------------------listMedicalReportOfPatientNotes--------------------: "+listMedicalReportOfPatient);
		return listMedicalReportOfPatient;
	}*/
}
