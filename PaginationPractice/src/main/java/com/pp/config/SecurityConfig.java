//package com.pp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
//	{
//		
//		return http
//				.csrf(csrf -> csrf.disable())
//				.cors(cors ->cors.disable())
//				.authorizeRequests(auth ->{
//					auth.anyRequest().permitAll();
//				})
//				.sessionManagement(sesion -> sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.build(); 
//				
//	}
//}
