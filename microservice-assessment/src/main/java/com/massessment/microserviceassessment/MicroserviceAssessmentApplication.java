package com.massessment.microserviceassessment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.massessment.microserviceassessment.beans.PatientBean;
import com.massessment.microserviceassessment.service.DiabetesAssessmentService;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.massessment.microserviceassessment")
public class MicroserviceAssessmentApplication implements CommandLineRunner {
	
	@Autowired
	private DiabetesAssessmentService diabetesAssessmentService;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAssessmentApplication.class, args);
	}
	
	//Testing request Microservice-patient and Microservice-MedicalReport with DiabetesAssessmentService
	@Override
	public void run(String... args) throws Exception {
		PatientBean patientBeanTest= diabetesAssessmentService.getPatientBean(1);
		System.out.println("********************PATIENT BEAN TEST FROM MICROSERVICE ASSESSMENT DIABETE ************************"+patientBeanTest);
	}
}
