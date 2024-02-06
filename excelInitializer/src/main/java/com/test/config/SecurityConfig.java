package com.test.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;;

@Configuration
@EnableWebSecurity
//@Order(10)
public class SecurityConfig {
	

	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		System.out.println("I am here!!!!!!");
		return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
//					auth.requestMatchers("/").permitAll();
//					auth.requestMatchers("/customer").permitAll();
//					auth.antMatchers(HttpMethod.POST).permitAll();
//					auth.anyRequest().authenticated();
//					auth.anyRequest().permitAll();
                    auth.anyRequest().permitAll();
                })
				.oauth2Login(withDefaults())
				.formLogin(withDefaults())
                .build();
				
	}
}
