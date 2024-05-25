package com.mmedicalreport.microservicemedicalReports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mmedicalreport.microservicemedicalReports.exceptions.CustomErrorDecoder;

@Configuration
public class FeignExceptionConfig {

	   @Bean
	   public CustomErrorDecoder mCustomErrorDecoder(){
	       return new CustomErrorDecoder();
	   }
}
