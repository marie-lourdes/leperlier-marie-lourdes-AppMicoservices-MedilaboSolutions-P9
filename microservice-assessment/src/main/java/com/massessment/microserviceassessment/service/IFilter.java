package com.massessment.microserviceassessment.service;

import com.massessment.microserviceassessment.beans.PatientBean;

public interface IFilter {
	boolean filterAgeLimitPatient(Integer id, Integer ageFiltered);
	String filterSexPatient(Integer id);
}
