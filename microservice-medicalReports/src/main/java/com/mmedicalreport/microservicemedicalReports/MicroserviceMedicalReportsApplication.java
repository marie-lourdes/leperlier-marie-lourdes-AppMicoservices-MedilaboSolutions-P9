package com.mmedicalreport.microservicemedicalReports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.mmedicalreport")
public class MicroserviceMedicalReportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMedicalReportsApplication.class, args);
	}

}
