package com.massessment.microserviceassessment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.massessment.microserviceassessment.service.CalculatorAgeImpl;
import com.massessment.microserviceassessment.service.CounterTermsMedicalReportNotesImpl;
import com.massessment.microserviceassessment.service.EvaluatorDiabeteImpl;
import com.massessment.microserviceassessment.service.FilterInfoPatientImpl;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.massessment.microserviceassessment")
public class MicroserviceAssessmentApplication implements CommandLineRunner {

	@Autowired
	private FilterInfoPatientImpl filterInfoPatientImpl;
	
	@Autowired
	CounterTermsMedicalReportNotesImpl counterTermsMedicalReportNotes;
	
	@Autowired
	EvaluatorDiabeteImpl evaluatorDiabete;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAssessmentApplication.class, args);
	}

	// Testing request Microservice-patient and Microservice-MedicalReport with FilterInfoPatientImpl
	@Override
	public void run(String... args) throws Exception {
		// *****************test calculator age*********************/
		CalculatorAgeImpl calculatorAge = new CalculatorAgeImpl();
		System.out.println(calculatorAge.calculateAge(LocalDate.of(1986, 8, 3), LocalDate.now()));

		int patientBeanTest = filterInfoPatientImpl.calculateAgeOfPatient(1);
		System.out.println(
				"********************PATIENT BEAN AGE CALCULATED TEST FROM MICROSERVICE ASSESSMENT DIABETE ************************"
						+ patientBeanTest);

		// *****************test countSymptom with 	CounterTermsMedicalReportNotesImpl*********************/
		List<String> notes=new ArrayList<>();
		notes.add("Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement");
		notes.add("Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale");
		Integer countSymptoms=counterTermsMedicalReportNotes.countSymptomFromMedicalReportNotes(notes);
		System.out.println(
				"********************COUNTER SYMPTOMS DIABETE************************"
						+  countSymptoms);
		
		// *****************test evaluator Diabete with 	EvaluatorDiabeteImpl *********************/
		System.out.println(
				"********************EVALUATOR DIABETE************************"
						+  	evaluatorDiabete.evaluateRiskDiabeteOfPatient(2));
	;
		
	}
}
