package com.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class WebSecurityConfig {
	
	public static final String[] PUBLIC_URLS = {
			"/api/auth/**",
			"/v2/api-docs",
			"/v3/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"
	};

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.cors().disable()
			.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers(PUBLIC_URLS).permitAll()
			.anyRequest()
			.authenticated()
			.and()
//			.exceptionHandling()
//			.authenticationEntryPoint(null)
//			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(null, null);
		
//		http.authenticationProvider(daoAuthenticationProvider());
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public DaoAuthenticationProvider daoAuthenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(null);
//		provider.setPasswordEncoder(passwordEncoder());
//		return provider;
//	}
//	
//	@Bean
//	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
//		// TODO Auto-generated method stub
//		return configuration.getAuthenticationManager();
//	}
}
