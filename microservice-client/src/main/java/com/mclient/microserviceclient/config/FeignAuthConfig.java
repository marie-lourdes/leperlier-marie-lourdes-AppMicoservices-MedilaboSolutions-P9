package com.mclient.microserviceclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignAuthConfig {
	 @Bean
	   public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor()

	{
	      return  new BasicAuthRequestInterceptor("root", "rootroot");
	   }
}
