package com.massessment.microserviceassessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.massessment.microserviceassessment.beans.PatientBean;
import com.massessment.microserviceassessment.proxy.IMicroserviceMedicalReportsProxy;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;

@Component
public class DiabetesAssessmentService {

	@Autowired
	private IMicroservicePatientsProxy microservicePatientsProxy;

	@Autowired
	private IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy;
	
	public PatientBean getPatientBean(Integer id) {
		PatientBean patientBean= microservicePatientsProxy.getPatientById(id);
		return patientBean;
		
	}
}
