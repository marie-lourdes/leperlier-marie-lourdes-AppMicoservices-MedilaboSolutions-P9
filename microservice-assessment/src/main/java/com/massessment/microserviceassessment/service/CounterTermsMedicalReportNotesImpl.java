package com.massessment.microserviceassessment.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CounterTermsMedicalReportNotesImpl implements ICounter {
	private static final Logger log = LogManager.getLogger(CounterTermsMedicalReportNotesImpl.class);

	private int numberOfSymptoms;
	private Set<String> symptoms = new HashSet<String>();

	private Set<String> setSymptoms() {
		symptoms.add("Hémoglobine A1C");
		symptoms.add("Microalbumine");
		symptoms.add("Taille");	
		symptoms.add("Poids");
		symptoms.add("Fumeur");
		symptoms.add("Fumeuse");	
		symptoms.add("Anormal");
		symptoms.add("Cholestérol");
		symptoms.add("Vertige");
		symptoms.add("Rechute");
		symptoms.add("Réaction");
		symptoms.add("Anticorps");
		
		return symptoms;
	}

	@Override
	public Integer countSymptomFromMedicalReportNotes(List<String> notes) {
		numberOfSymptoms = 0;
		log.debug("Counting  number of symptoms in notes of patient: {}", notes);
		this.setSymptoms();
		for (String symptom : symptoms) {
			for (String note : notes) {			
				if(StringUtils.containsIgnoreCase(note, symptom)) {;
					numberOfSymptoms += 1;
					;
				}
			}
		}
		log.debug("Number occurrence of list symptoms in notes medicalreport"+numberOfSymptoms);
		return numberOfSymptoms;
	}
}
