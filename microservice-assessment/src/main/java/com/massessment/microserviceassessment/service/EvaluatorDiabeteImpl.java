package com.massessment.microserviceassessment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.massessment.microserviceassessment.beans.MedicalReportBean;
import com.massessment.microserviceassessment.proxy.IMicroserviceMedicalReportsProxy;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;

@Component
public class EvaluatorDiabeteImpl  implements IEvaluatorRiskDiabete{
	private ICounter counterTermsMedicalReportNotes;
	private IFilter filterInfoPatient;
	private IMicroservicePatientsProxy microservicePatientsProxy;
	private IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy;
	private Integer numberOfSymptoms;
	
	public EvaluatorDiabeteImpl (IMicroservicePatientsProxy microservicePatientsProxy,
			IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy, IFilter filterInfoPatient,ICounter counterTermsMedicalReportNotes
			) {
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
	public boolean evaluateAsRiskNone(MedicalReportBean medicalReport ) {
		//List<String>  patientBean =this.getMedicalReportNotes(medicalReport.getPatId());
		// getMedicalReportNotes(patientBean.) 
		return false;
	}
	
	@Override
	public boolean   evaluateAsRiskBorderLine(MedicalReportBean medicalReport) {
		return false;
	}

	@Override
	public boolean   evaluateAsRiskDanger(MedicalReportBean medicalReport) {
		return false;
	}
	
	@Override
	public boolean  evaluateAsRiskEarlyOnSet(MedicalReportBean medicalReport) {
		return false;
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
