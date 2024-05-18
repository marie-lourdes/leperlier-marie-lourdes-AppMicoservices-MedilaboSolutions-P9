package com.massessment.microserviceassessment.service;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.massessment.microserviceassessment.beans.PatientBean;
import com.massessment.microserviceassessment.proxy.IMicroserviceMedicalReportsProxy;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;

@Component
public class FilterInfoPatientImpl implements IFilter{
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

	@Override
	public boolean filterRiskBorderLine(PatientBean patient) {
		boolean isLessThan30years= false;
		isLessThan30years= calculateAgeOfPatient(patient.getId()) < 30 ? true : false;
		 return  isLessThan30years;
	}
	@Override
	public String filterSexPatient(PatientBean patient) {
		String masculin= "M";
		String feminin= "F";
		PatientBean patientBean = this.getPatientBean(patient.getId());
		 ageLessThan30= patientBean.getGenre().equals(masculin)? masculin : feminin ;
	}
	private PatientBean getPatientBean(Integer id) {
		return microservicePatientsProxy.getPatientById(id);
	}
}
