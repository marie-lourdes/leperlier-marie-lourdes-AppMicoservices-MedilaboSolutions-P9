package com.massessment.microserviceassessment.service;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.massessment.microserviceassessment.beans.PatientBean;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;
import com.massessment.microserviceassessment.utils.Constants;

@Component
public class FilterInfoPatientImpl implements IFilter {
	private IMicroservicePatientsProxy microservicePatientsProxy;
	private ICalculatorAge calculatorAge;

	public FilterInfoPatientImpl(IMicroservicePatientsProxy microservicePatientsProxy) {
		this.microservicePatientsProxy = microservicePatientsProxy;
		this.calculatorAge = new CalculatorAgeImpl();

	}

	@Override
	public boolean filterAgeLimitPatient(Integer id, Integer ageLimitFiltered) {
		boolean isLessAgeFiltered = false;
		isLessAgeFiltered = calculateAgeOfPatient(id) > ageLimitFiltered ? true : false;
		return isLessAgeFiltered;
	}

	public int calculateAgeOfPatient(Integer id) {
		PatientBean patientBean = microservicePatientsProxy.getPatientById(id);
		return calculatorAge.calculateAge(patientBean.getDateDeNaissance(), LocalDate.now());
	}

	@Override
	public String filterSexPatient(Integer id) {
		PatientBean patientBean = microservicePatientsProxy.getPatientById(id);
		return patientBean.getGenre().equals(Constants.MASCULIN) ? Constants.MASCULIN : Constants.FEMININ;

	}

}
