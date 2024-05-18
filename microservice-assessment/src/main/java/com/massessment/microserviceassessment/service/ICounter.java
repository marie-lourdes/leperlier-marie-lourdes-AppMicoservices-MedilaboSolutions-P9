package com.massessment.microserviceassessment.service;

import java.util.List;

public interface ICounter {
	Integer countSymptomFromMedicalReportNotes(List<String> notes);
}
