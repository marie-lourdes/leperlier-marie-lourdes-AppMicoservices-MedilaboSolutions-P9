package com.massessment.microserviceassessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.massessment.microserviceassessment.exceptions.CustomErrorDecoder;

@Configuration
public class FeignExceptionConfig {

	   @Bean
	   public CustomErrorDecoder mCustomErrorDecoder(){
	       return new CustomErrorDecoder();
	   }
}
