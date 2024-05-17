package com.mclient.microserviceclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestBody;

import com.mclient.microserviceclient.bean.MedicalReportBean;
import com.mclient.microserviceclient.bean.PatientBean;
import com.mclient.microserviceclient.proxy.IMicroserviceMedicalReportsProxy;
import com.mclient.microserviceclient.proxy.IMicroservicePatientsProxy;

import jakarta.validation.Valid;

@SpringBootApplication
@EnableFeignClients("com.mclient")
public class MicroserviceClientApplication implements CommandLineRunner {
	@Autowired
	private IMicroservicePatientsProxy microservicePatientsProxy;
	@Autowired
	private IMicroserviceMedicalReportsProxy medicalReportsProxy;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*PatientBean PatientFoundById = microservicePatientsProxy.getPatientById(1);
		List<MedicalReportBean> medicalReportFoundByPatId = medicalReportsProxy.getPatientByPatId(1);
		System.out.println(
				"*************PATIENT  FROM MICROSERVICE CLIENT patient id: {}, {}*********" + PatientFoundById);
		medicalReportFoundByPatId.forEach(medicalReport -> System.out.println(
				"*************MEDICAL REPORT PATIENT FROM MICROSERVICE CLIENT BEFORE NOTE CREATED : {}, {}*********" + medicalReport));

		MedicalReportBean medicalReportToCreate = new MedicalReportBean();
		List<String> notes = new ArrayList<>();
		notes.add(" note for testing method post from commandlinerunner of microservice client");
		medicalReportToCreate.setNote(notes);
		MedicalReportBean medicalReportCreated = medicalReportsProxy.createMedicalReport(PatientFoundById.getId(),
				medicalReportToCreate);
		System.out.println("*************MEDICAL REPORT NOTE CREATED FROM MICROSERVICE CLIENT : {}, {}*********"
				+ medicalReportCreated);
		List<MedicalReportBean> medicalReportFoundByPatIdNoteCreated = medicalReportsProxy.getPatientByPatId(1);
		medicalReportFoundByPatIdNoteCreated.forEach(medicalReport -> System.out.println(
				"*************MEDICAL REPORT PATIENT WITH NOTE CREATED FROM MICROSERVICE CLIENT : {}, {}*********"
						+ medicalReport));*/
	}
}
