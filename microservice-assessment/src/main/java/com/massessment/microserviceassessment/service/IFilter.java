package com.massessment.microserviceassessment.service;

import com.massessment.microserviceassessment.beans.PatientBean;

public interface IFilter {
	boolean filterAgePatient(PatientBean patient);
	String filterSexPatient(PatientBean patient);
}
