package com.mmedicalreport.microservicemedicalreports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mmedicalreport.microservicemedicalreports.exceptions.CustomErrorDecoder;

@Configuration
public class FeignExceptionConfig {

	   @Bean
	   public CustomErrorDecoder mCustomErrorDecoder(){
	       return new CustomErrorDecoder();
	   }
}
