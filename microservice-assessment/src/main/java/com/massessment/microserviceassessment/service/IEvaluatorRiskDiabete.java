package com.massessment.microserviceassessment.service;

import com.massessment.microserviceassessment.beans.MedicalReportBean;

public interface IEvaluatorRiskDiabete {
	//several methods with same signature , but will be possible override them later  with rules different in class impl
	boolean evaluateAsRiskNone(Integer id,MedicalReportBean medicalReport);
	boolean  evaluateAsRiskBorderLine(MedicalReportBean medicalReport);
	boolean  evaluateAsRiskDanger(MedicalReportBean medicalReport);
	boolean  evaluateAsRiskEarlyOnSet(MedicalReportBean medicalReport);
}
