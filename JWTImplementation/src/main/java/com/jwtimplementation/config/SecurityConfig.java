package com.jwtimplementation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwtimplementation.service.JWTAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		//configure security filter chain
		
		return httpSecurity
				.csrf(csrf->csrf.disable()) //disable csrf
				.authorizeHttpRequests(auth->auth
						.requestMatchers("/auth/**").permitAll()
						.anyRequest().authenticated()
						)
				.sessionManagement(session-> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						)
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationEntryPoint,UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}
