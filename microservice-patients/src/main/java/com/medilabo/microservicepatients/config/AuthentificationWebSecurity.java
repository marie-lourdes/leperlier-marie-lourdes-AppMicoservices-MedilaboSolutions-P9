package com.medilabo.microservicepatients.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthentificationWebSecurity {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> {
			request.requestMatchers("/patient/**").hasRole("ADMIN");
			request.requestMatchers(HttpMethod.GET,"/patient/").hasRole("USER");
			request.requestMatchers("/h2-console/**").permitAll();
			request.anyRequest().authenticated();

		}).formLogin(Customizer.withDefaults()).oauth2Login(Customizer.withDefaults()).logout((logout) -> logout.permitAll());
				
		http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));// desactive le header securité cors pour le path vers la bdd H2
		return http.build();
	}

}
