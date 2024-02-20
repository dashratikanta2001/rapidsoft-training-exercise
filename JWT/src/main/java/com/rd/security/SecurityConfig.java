package com.rd.security;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rd.filter.CustomAuthenticationFilter;
import com.rd.filter.CustomAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
		customAuthenticationFilter.setFilterProcessesUrl("/api/login");
		
		http.csrf().disable()
			.sessionManagement().sessionCreationPolicy(STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers("/api/login","/api/token/refresh/**").permitAll()
			.antMatchers(GET, "/api/users/**").hasAnyAuthority("ROLE_USER")
			.antMatchers(POST, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers(POST, "/api/role/**").hasAnyAuthority("ROLE_ADMIN")
			.anyRequest().authenticated()
			.and()
			.addFilter(customAuthenticationFilter)
			.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	
	
}
