package com.massessment.microserviceassessment.service;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.massessment.microserviceassessment.beans.PatientBean;
import com.massessment.microserviceassessment.proxy.IMicroserviceMedicalReportsProxy;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;

import lombok.Data;

@Data
@Component
public class DiabetesAssessmentService {

	private IMicroservicePatientsProxy microservicePatientsProxy;
	private IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy;
	private ICalculatorAge  calculatorAge;
	
	public DiabetesAssessmentService( IMicroservicePatientsProxy microservicePatientsProxy,
			IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy){
		this.microservicePatientsProxy= microservicePatientsProxy;
		this.microserviceMedicalReportsProxy= microserviceMedicalReportsProxy;
		this.calculatorAge= new CalculatorAgeImpl();
		
	}

	public int calculateAgeOfPatient(Integer id) {
		PatientBean patientBean=this.getPatientBean(id);
		return calculatorAge.calculateAge(patientBean.getDateDeNaissance(), LocalDate.now());
	}


	
	private  PatientBean getPatientBean(Integer id) {
		return microservicePatientsProxy.getPatientById(id);		
	}
	
}
