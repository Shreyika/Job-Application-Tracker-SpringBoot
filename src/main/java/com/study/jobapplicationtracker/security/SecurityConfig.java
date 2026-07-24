package com.study.jobapplicationtracker.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.study.jobapplicationtracker.jwt.AuthTokenFilter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity
		.csrf(csrf->csrf.disable())
		.cors(Customizer.withDefaults())
		.authorizeHttpRequests(request->
		request
		.requestMatchers(HttpMethod.GET,"/products/**","/categories","/jobs").permitAll()
		.requestMatchers(HttpMethod.POST,"/users","/auth/login","/recruiters/register/**","/candidates/register/**").permitAll()
		.requestMatchers(HttpMethod.POST,"/recruiters","/candidates").hasRole("RECRUITER")
		.requestMatchers(HttpMethod.DELETE,"/recruiters/**","/categories/**").hasRole("RECRUITER") //  "/**" because delete url is /products/2, /3
		.requestMatchers(HttpMethod.PUT,"/recruiters/**","/candidates/**").hasRole("RECRUITER")
		.anyRequest().authenticated()
		);
		
		httpSecurity.httpBasic(Customizer.withDefaults());
		
		httpSecurity.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
		
		httpSecurity.exceptionHandling(authentication->authentication.authenticationEntryPoint(authenticationEntryPoint));
		
		return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
	{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
        
    }
	
}
