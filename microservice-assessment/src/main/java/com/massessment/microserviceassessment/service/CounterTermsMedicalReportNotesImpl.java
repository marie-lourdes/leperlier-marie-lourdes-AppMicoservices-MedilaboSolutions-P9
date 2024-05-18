package com.massessment.microserviceassessment.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CounterTermsMedicalReportNotesImpl implements ICounter {

	private Integer numberOfSymptoms;
	private HashSet<String> symptoms = new HashSet<String>();

	private HashSet<String> setSymptoms() {
		symptoms.add("Hémoglobine A1C");
		symptoms.add("Microalbumine");
		symptoms.add("Taille");
		symptoms.add("Fumeur, Fumeuse");
		symptoms.add("Anormal");
		symptoms.add("Cholestérol …");
		symptoms.add("Vertiges");
		symptoms.add("Rechute");
		symptoms.add("Réaction ");
		symptoms.add("Anticorps");
		return symptoms;
	}

	@Override
	public Integer countSymptomFromMedicalReportNotes(List<String> notes) {
		numberOfSymptoms = 0;

		this.setSymptoms();
		for (String symptom : symptoms) {

			for (String note : notes) {
				System.out.println(symptom);
				System.out.println(note);

				if (note.contains(symptom)) {
					numberOfSymptoms += 1;
				}
			}
		}
		return numberOfSymptoms;
	}
}
