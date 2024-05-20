package com.massessment.microserviceassessment.service;

import org.springframework.stereotype.Service;

import com.massessment.microserviceassessment.proxy.IMicroserviceMedicalReportsProxy;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;

import lombok.Data;

/* 
 * this class apply Design Pattern Strategy with class  EvaluatorDiabeteImpl and abstraction  Interface IEvaluatorRiskDiabete
 * 
 */
@Data
@Service
public class DiabetesAssessmentService implements IEvaluatorRiskDiabete  {
	private ICounter counterTermsMedicalReportNotes;
	private IFilter filterInfoPatient;
	private IMicroservicePatientsProxy microservicePatientsProxy;
	private IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy;
	private Integer numberOfSymptoms;
	private IEvaluatorRiskDiabete evaluatorDiabete;


	public DiabetesAssessmentService(IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy,
			IMicroservicePatientsProxy microservicePatientsProxy, ICounter counterTermsMedicalReportNotes) {
		
		this.microservicePatientsProxy = microservicePatientsProxy;
		this.microserviceMedicalReportsProxy = microserviceMedicalReportsProxy;
		this.counterTermsMedicalReportNotes = counterTermsMedicalReportNotes;
		this.evaluatorDiabete = new EvaluatorDiabeteImpl(microservicePatientsProxy,microserviceMedicalReportsProxy,counterTermsMedicalReportNotes);	
	}
	
	@Override
	public String evaluateRiskDiabeteOfPatient(Integer id) throws NullPointerException{
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
