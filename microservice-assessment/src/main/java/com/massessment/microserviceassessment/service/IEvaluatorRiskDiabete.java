package com.massessment.microserviceassessment.service;

import com.massessment.microserviceassessment.beans.MedicalReportBean;

public interface IEvaluatorRiskDiabete {
	//several methods with same signature , but will be possible override them later  with rules different in class impl
	String evaluateAsRiskNone(Integer id,MedicalReportBean medicalReport);
	String evaluateAsRiskBorderLine(MedicalReportBean medicalReport);
	String  evaluateAsRiskDanger(MedicalReportBean medicalReport);
	String evaluateAsRiskEarlyOnSet(MedicalReportBean medicalReport);
}
