package com.test.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http
				.authorizeHttpRequests(auth ->{
//					auth.requestMatchers("/").permitAll();
//					auth.requestMatchers("/customer").permitAll();
//					auth.anyRequest().authenticated();
					auth.anyRequest().permitAll();
				})
				.oauth2Login(withDefaults())
				.formLogin(withDefaults())
				.build();
				
	}
}
