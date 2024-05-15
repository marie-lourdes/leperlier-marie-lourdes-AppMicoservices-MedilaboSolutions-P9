package com.mmedicalreport.microservicemedicalReports;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceMedicalReportsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMedicalReportsApplication.class, args);
	}
	
	//Testing MongoRepository and custom method findByPatient()
	@Override
	public void run(String... args) throws Exception {
		 
	}
	
}
