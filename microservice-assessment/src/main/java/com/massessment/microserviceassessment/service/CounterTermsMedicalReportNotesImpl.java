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
	
	
/*    switch (symptomType) {

    // Case statements
    case GOT:
        System.out.println("Game of Thrones selected");
        break;
    case Breakingbad:
        System.out.println("Breaking Bad selected");
        break;
    case Lucifer:
        System.out.println("Lucifer selected");
        break;
    case Boys:
        System.out.println("Boys selected");
        break;
    case Mirzapur:
        System.out.println("Mirzapur selected");
        break;
    case Moneyheist:
        System.out.println("Money Heist selected");
        break;
    default:
        System.out.println("You are outdated !!!");
        break;
    }*/
	
	

}
