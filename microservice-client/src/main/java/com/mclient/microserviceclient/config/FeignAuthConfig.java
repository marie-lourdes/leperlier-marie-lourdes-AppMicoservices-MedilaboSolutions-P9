package com.mclient.microserviceclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignAuthConfig {

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor()

	{
		//return new BasicAuthRequestInterceptor("root", passwordEncoder().encode("rootroot"));
		return new BasicAuthRequestInterceptor("root", "rootroot");
	}

	/*@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/
}
