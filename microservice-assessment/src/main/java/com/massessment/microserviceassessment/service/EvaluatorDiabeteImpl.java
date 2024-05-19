package com.massessment.microserviceassessment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.massessment.microserviceassessment.beans.MedicalReportBean;
import com.massessment.microserviceassessment.beans.PatientBean;
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
	private String riskEvaluated;

	public EvaluatorDiabeteImpl(IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy,IMicroservicePatientsProxy microservicePatientsProxy,
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

	public String evaluateRiskDiabeteOfPatient(Integer id) throws NullPointerException {
		PatientBean patientBean= this.getPatientBean( id);
		riskEvaluated="";
		riskEvaluated=evaluateAsRiskNone(patientBean.getId())? ConstantRiskDiabete.RISK_NONE:null;
		if(riskEvaluated==null) {
			System.out.println("Failed to evaluate risk diabete for patient "+ id);
		}
		return riskEvaluated;
				
	}
	
	@Override
	public boolean evaluateAsRiskNone(Integer id) {
		numberOfSymptoms = countSymptomFromMedicalReportNotes(id);
		return numberOfSymptoms == 0 ? true : false;
	}

	@Override
	public boolean evaluateAsRiskBorderLine(MedicalReportBean medicalReport) {
		return false;
	}

	@Override
	public boolean evaluateAsRiskDanger(MedicalReportBean medicalReport) {
		return false;
	}

	@Override
	public boolean evaluateAsRiskEarlyOnSet(MedicalReportBean medicalReport) {
		return false;
	}

	public List<MedicalReportBean> getMedicalReportBean(Integer patientId) {
		return microserviceMedicalReportsProxy.getMedicalReportsByPatId(patientId);
	}
	

	private PatientBean getPatientBean(Integer id) {
		return microservicePatientsProxy.getPatientById(id);
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
