package com.mclient.microserviceclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignAuthConfig {
	
	public String username= "root";
	public String password= "rootroot";
	 @Bean
	   public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor()

	{
	      return  new BasicAuthRequestInterceptor(passwordEncoder().encode("root"), passwordEncoder().encode("rootroot"));
	   }
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
}
