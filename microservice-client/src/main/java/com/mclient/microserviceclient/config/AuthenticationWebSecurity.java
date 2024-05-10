package com.mclient.microserviceclient.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class AuthenticationWebSecurity {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> {
			//request.requestMatchers(HttpMethod.GET, "/home").hasRole("USER");
			request.requestMatchers("/home/patient/all-patients").hasRole("USER");
			request.requestMatchers("/home/patient/**").hasRole("ADMIN");	
			request.requestMatchers("/home").permitAll();	
			request.anyRequest().authenticated();

		}).httpBasic(Customizer.withDefaults());

		//http.csrf(csrf -> csrf.ignoringRequestMatchers("/home/patient/validateFormPatient")); //desactive le header securité cors pour le path 
		return http.build();
	}

	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder().username("user").password(passwordEncoder().encode("user")).roles("USER")
				.build();
		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin"))
				.roles("ADMIN","USER").build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
