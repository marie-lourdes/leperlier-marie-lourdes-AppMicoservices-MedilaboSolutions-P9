package com.massessment.microserviceassessment.service;

import org.springframework.stereotype.Service;

import com.massessment.microserviceassessment.proxy.IMicroserviceMedicalReportsProxy;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;

import lombok.Data;

@Data
@Service
public class DiabetesAssessmentService implements IEvaluatorRiskDiabete {

	private IMicroservicePatientsProxy microservicePatientsProxy;
	private IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy;
	private Integer numberOfSymptoms;
	private IEvaluatorRiskDiabete evaluatorDiabete;

	public DiabetesAssessmentService(IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy,
			IMicroservicePatientsProxy microservicePatientsProxy, ICounter counterTermsMedicalReportNotes,
			IFilter filterInfoPatient) {

		this.microservicePatientsProxy = microservicePatientsProxy;
		this.microserviceMedicalReportsProxy = microserviceMedicalReportsProxy;
		this.evaluatorDiabete = new EvaluatorDiabeteImpl(microservicePatientsProxy, microserviceMedicalReportsProxy,
				new CounterTermsMedicalReportNotesImpl(), new FilterInfoPatientImpl(microservicePatientsProxy));
	}

	@Override
	public String evaluateRiskDiabeteOfPatient(Integer id) throws NullPointerException {
		return evaluatorDiabete.evaluateRiskDiabeteOfPatient(id);
	}

	@Override
	public String evaluateAsRiskNone(Integer id) {
		return evaluatorDiabete.evaluateAsRiskNone(id);
	}

	@Override
	public String evaluateAsRiskBorderLine(Integer id) {
		return evaluatorDiabete.evaluateAsRiskBorderLine(id);
	}

	@Override
	public String evaluateAsRiskDanger(Integer id) {
		return evaluatorDiabete.evaluateAsRiskDanger(id);
	}

	@Override
	public String evaluateAsRiskEarlyOnSet(Integer id) {
		return evaluatorDiabete.evaluateAsRiskEarlyOnSet(id);
	}

}
