package com.massessment.microserviceassessment.service;

import java.time.LocalDate;

import com.massessment.microserviceassessment.beans.PatientBean;
import com.massessment.microserviceassessment.proxy.IMicroserviceMedicalReportsProxy;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;

public class FilterInfoPatientImpl {
	private IMicroservicePatientsProxy microservicePatientsProxy;
	private IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy;
	private ICalculatorAge calculatorAge;

	public  FilterInfoPatientImpl(IMicroservicePatientsProxy microservicePatientsProxy,
			IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy) {
		this.microservicePatientsProxy = microservicePatientsProxy;
		this.microserviceMedicalReportsProxy = microserviceMedicalReportsProxy;
		this.calculatorAge = new CalculatorAgeImpl();

	}

	public int calculateAgeOfPatient(Integer id) {
		PatientBean patientBean = this.getPatientBean(id);
		return calculatorAge.calculateAge(patientBean.getDateDeNaissance(), LocalDate.now());
	}

	private PatientBean getPatientBean(Integer id) {
		return microservicePatientsProxy.getPatientById(id);
	}
}
