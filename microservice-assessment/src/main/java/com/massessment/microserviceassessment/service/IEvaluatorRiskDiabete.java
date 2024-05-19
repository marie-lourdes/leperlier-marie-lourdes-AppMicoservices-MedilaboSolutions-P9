package com.massessment.microserviceassessment.service;

import com.massessment.microserviceassessment.beans.PatientBean;

public interface IEvaluatorRiskDiabete {
	//several methods with same signature , but will be possible override them later  with rules different in class impl
	boolean evaluateAsRiskNone(PatientBean patient);
	boolean  evaluateAsRiskBorderLine(PatientBean patient);
	boolean  evaluateAsRiskDanger(PatientBean patient);
	boolean  evaluateAsRiskEarlyOnSet(PatientBean patient);
}
