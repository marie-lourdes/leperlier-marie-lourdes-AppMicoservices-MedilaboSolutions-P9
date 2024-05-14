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

import com.mmedicalreport.microservicemedicalReports.model.MedicalReport;
import com.mmedicalreport.microservicemedicalReports.repository.IMedicalReportRepository;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.mmedicalreport")
public class MicroserviceMedicalReportsApplication implements CommandLineRunner{
	private static final Logger log = LogManager.getLogger(MicroserviceMedicalReportsApplication.class);
     @Autowired
    private  IMedicalReportRepository medicalReportRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMedicalReportsApplication.class, args);
	}
	
	//Testing MongoRepository and custom method findByPatient()
	@Override
	public void run(String... args) throws Exception {
		 Optional<MedicalReport> medicalReportFoundByPatient = medicalReportRepository.findByPatient("TestNone");
		 if ( medicalReportFoundByPatient .isPresent()) {
			 log.info("MedicalReport find by Patient name {}", medicalReportFoundByPatient .get().getNote().get(0));
     } else {
    	 log.error("MediaclaReport not found");
     }
		 
	}

}
