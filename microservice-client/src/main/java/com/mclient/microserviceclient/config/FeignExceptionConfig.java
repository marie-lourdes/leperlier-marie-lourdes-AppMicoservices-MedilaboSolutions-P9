package com.mclient.microserviceclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mclient.microserviceclient.exceptions.CustomErrorDecoder;

@Configuration
public class FeignExceptionConfig {

	   @Bean
	   public CustomErrorDecoder mCustomErrorDecoder(){
	       return new CustomErrorDecoder();
	   }
}
