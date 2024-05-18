package com.massessment.microserviceassessment.service;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.massessment.microserviceassessment.beans.PatientBean;
import com.massessment.microserviceassessment.proxy.IMicroserviceMedicalReportsProxy;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;
import com.massessment.microserviceassessment.utils.Constants;

@Component
public class FilterInfoPatientImpl implements IFilter {
	private IMicroservicePatientsProxy microservicePatientsProxy;
	private IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy;
	private ICalculatorAge calculatorAge;

	public FilterInfoPatientImpl(IMicroservicePatientsProxy microservicePatientsProxy,
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
	public boolean filterRiskNone(PatientBean patient) {
		return false;
	}
	
	@Override
	public boolean filterRiskBorderLine(PatientBean patient) {
		boolean isLessThan30years = false;
		isLessThan30years = calculateAgeOfPatient(patient.getId()) < 30 ? true : false;
		return isLessThan30years;
	}

	@Override
	public boolean filterRiskDanger(PatientBean patient) {
		return false;
	}
	
	@Override
	public boolean  filterRiskEarlyOnSet(PatientBean patient) {
		return false;
	}
	
	@Override
	public String filterSexPatient(PatientBean patient) {
		PatientBean patientBean = this.getPatientBean(patient.getId());
		String isMasculinOrFeminin = patientBean.getGenre().equals(Constants.MASCULIN) ? Constants.MASCULIN
				: Constants.FEMININ;
		return isMasculinOrFeminin;
	}

	private PatientBean getPatientBean(Integer id) {
		return microservicePatientsProxy.getPatientById(id);
	}
}
