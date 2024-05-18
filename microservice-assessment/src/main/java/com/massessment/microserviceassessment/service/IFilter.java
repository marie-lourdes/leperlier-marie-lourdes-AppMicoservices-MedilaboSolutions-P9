package com.massessment.microserviceassessment.service;

import com.massessment.microserviceassessment.beans.PatientBean;

public interface IFilter {
	//several methods with same signature , but will be possible override them later  with rules different in class impl
	boolean filterRiskNone(PatientBean patient);
	boolean filterRiskBorderLine(PatientBean patient);
	boolean filterRiskDanger(PatientBean patient);
	boolean filterRiskEarlyOnSet(PatientBean patient);
	
	String filterSexPatient(PatientBean patient);
}
