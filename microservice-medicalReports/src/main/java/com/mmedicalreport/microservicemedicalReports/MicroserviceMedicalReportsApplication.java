package com.mmedicalreport.microservicemedicalReports;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.mmedicalreport.microservicemedicalReports.bean.PatientBean;
import com.mmedicalreport.microservicemedicalReports.model.MedicalReport;
import com.mmedicalreport.microservicemedicalReports.proxy.IMicroservicePatientsProxy;
import com.mmedicalreport.microservicemedicalReports.repository.IMedicalReportRepository;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.mmedicalreport")
public class MicroserviceMedicalReportsApplication implements CommandLineRunner{
	private static final Logger log = LogManager.getLogger(MicroserviceMedicalReportsApplication.class);
     @Autowired
    private  IMedicalReportRepository medicalReportRepository;
     @Autowired
    private IMicroservicePatientsProxy  microservicePatientsProxy;
	
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMedicalReportsApplication.class, args);
	}
	
	//Testing MongoRepository and custom method findByPatient()
	@Override
	public void run(String... args) throws Exception {
		 Optional<MedicalReport> medicalReportFoundByPatient = medicalReportRepository.findByPatient("TestNone");
		// Optional<MedicalReport> medicalReportFoundById = medicalReportRepository.findByPatId(1);
		 if ( medicalReportFoundByPatient .isPresent()) {
			 log.info("MedicalReport find by Patient name {} id: {}", medicalReportFoundByPatient.get().getNote().get(0),medicalReportFoundByPatient.get().getPatId());
     } else {
    	 log.error("MedicalReport not found");
     }
		// log.info("MedicalReport find by patient  id: {}",  medicalReportFoundById.get().getPatId());
	
		 PatientBean patientFoundById = new PatientBean();
		Integer  medicalReportPatId =medicalReportRepository.getByPatId("1").get().getPatId();
			/*patientFoundById = microservicePatientsProxy.getPatientById(  medicalReportPatId  );
			 if ( medicalReportPatId != null) {
				 log.info("patientFoundById: {}",  patientFoundById.getId());
	     } else {
	    	 log.error("patientFoundById Proxy ");
	     }*/
	
	}
	
}
