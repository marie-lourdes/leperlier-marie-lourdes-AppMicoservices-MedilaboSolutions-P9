package com.massessment.microserviceassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.massessment.microserviceassessment")
public class MicroserviceAssessmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAssessmentApplication.class, args);
	}
}
