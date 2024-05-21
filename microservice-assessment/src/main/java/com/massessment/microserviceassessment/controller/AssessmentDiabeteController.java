package com.massessment.microserviceassessment.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.massessment.microserviceassessment.service.DiabetesAssessmentService;

@RestController
public class AssessmentDiabeteController {
	private static final Logger log = LogManager.getLogger(AssessmentDiabeteController.class);

	@Autowired
	DiabetesAssessmentService diabetesAssessmentService;

	@GetMapping("/patient/assessment-diabetes/{id}")
	public String evaluateRiskDiabetePatientById(@PathVariable Integer id) {

		String riskEvaluated = null;
		try {
			riskEvaluated = diabetesAssessmentService.evaluateRiskDiabeteOfPatient(id);
		} catch (NullPointerException e) {
			log.error("Failed to evaluate risk diabete for patient id: {} ", id);
		}

		log.info("Risk evaluated successfully retrieved for patient id : {}, {}", id, riskEvaluated);
		return riskEvaluated;
	}
}
