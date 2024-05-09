package com.medilabo.microserviceAPIGateway.config;


@Configuration
@EnableWebSecurity
public class AuthenticationWebSecurity {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> {
			request.requestMatchers(HttpMethod.GET, "/patient/**").hasRole("USER");
			request.requestMatchers("/patient/**").hasRole("ADMIN");	
			//request.requestMatchers("/h2-console/**").permitAll();
			request.anyRequest().authenticated();

		}).httpBasic(Customizer.withDefaults());

		http.csrf(csrf -> csrf.ignoringRequestMatchers("/patient/**"));// desactive le header securité cors pour le path 
		return http.build();
	}

	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder().username("user").password(passwordEncoder().encode("user")).roles("USER")
				.build();
		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin"))
				.roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
