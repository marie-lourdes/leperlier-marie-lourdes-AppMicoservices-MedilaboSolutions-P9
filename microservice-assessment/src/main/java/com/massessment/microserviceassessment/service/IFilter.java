package com.massessment.microserviceassessment.service;

import com.massessment.microserviceassessment.beans.PatientBean;

public interface IFilter {
	boolean filterAgeLimitPatient(PatientBean patient, Integer ageFiltered);
	String filterSexPatient(PatientBean patient);
}
