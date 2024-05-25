package com.mmedicalreport.microservicemedicalreports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.mmedicalreport.microservicemedicalreports")
public class MicroserviceMedicalReportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMedicalReportsApplication.class, args);
	}	
}
