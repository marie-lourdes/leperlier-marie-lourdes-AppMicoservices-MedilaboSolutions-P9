package com.massessment.microserviceassessment.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CounterTermsMedicalReportNotesImpl implements ICounter {

	private Integer numberOfSymptoms;
	private Set<String> symptoms = new HashSet<String>();

	private Set<String> setSymptoms() {
		//symptoms.add("Hémoglobine A1C");
		symptoms.add("hémoglobine A1C");
		//symptoms.add("Microalbumine");
		symptoms.add("microalbumine");
		//symptoms.add("Taille");
		symptoms.add("taille");
		//symptoms.add("Poids");
	    symptoms.add("poids");
		//symptoms.add("Fumeur");
		//symptoms.add("Fumeuse");
		symptoms.add("fumeur");
		symptoms.add("fumeuse");
		//symptoms.add("Anormal");
		symptoms.add("anormal");
		//symptoms.add("Cholestérol");
		symptoms.add("cholestérol");
		//symptoms.add("Vertige");
		symptoms.add("vertige");
		//symptoms.add("Rechute");
		symptoms.add("rechute");
		//symptoms.add("Réaction");
		symptoms.add("réaction");
		//symptoms.add("Anticorps");
		symptoms.add("anticorps");
		return symptoms;
	}

	@Override
	public Integer countSymptomFromMedicalReportNotes(List<String> notes) {
		numberOfSymptoms = 0;

		this.setSymptoms();
		for (String symptom : symptoms) {
			for (String note : notes) {
				if(StringUtils.containsIgnoreCase(note, symptom)) {;
				//if (note.contains(symptom)) {
					System.out.println("symptom occurrence: "+symptom);
					System.out.println("line note: "+note);
					numberOfSymptoms += 1;
					System.out.println("----------------------number symptoms----------------------------- "+numberOfSymptoms);
				}
			}
		}
		System.out.println("number occurrence of list symptoms in notes medicalreport"+numberOfSymptoms);
		return numberOfSymptoms;
	}
}
