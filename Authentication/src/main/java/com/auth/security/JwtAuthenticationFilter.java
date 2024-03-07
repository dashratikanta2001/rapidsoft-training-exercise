package com.auth.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth.dto.CustomResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtUtil;
	
	@Autowired
	private Jackson2ObjectMapperBuilder objectMapperBuilder;

	public static final String[] PUBLIC_URLS = {
			"/api/auth/**",
			"/v2/api-docs",
			"/v3/api-docs",
			"/swagger-resources/**",
			"/swagger-resources/**",
			"/webjars/**"
	};
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		
		if(request.getRequestURI().startsWith("/api/auth/") 
				|| request.getRequestURI().startsWith("/v2/api-docs")
				|| request.getRequestURI().startsWith("/v3/api-docs")
				|| request.getRequestURI().startsWith("/swagger-resources")
				|| request.getRequestURI().startsWith("/swagger-ui")
				|| request.getRequestURI().startsWith("/webjars"))
		{
			
		}

		else if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = this.jwtUtil.extractUsername(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get Jwt token");
				sendJsonResponse(response, new CustomResponse(HttpStatus.UNAUTHORIZED.value(), null, "Unable to get token"), HttpStatus.UNAUTHORIZED);
				return;
			} catch (ExpiredJwtException e) {
				// TODO: handle exception
				System.out.println("Jwt token has expired");
				sendJsonResponse(response, new CustomResponse(HttpStatus.UNAUTHORIZED.value(), null, "Token has expired"), HttpStatus.UNAUTHORIZED);
				return;
			} catch (MalformedJwtException e) {
				// TODO: handle exception
				System.out.println("Invalid jwt");
				sendJsonResponse(response, new CustomResponse(HttpStatus.UNAUTHORIZED.value(), null, "Invalid Token"), HttpStatus.UNAUTHORIZED);
				return;
			}
			catch (SignatureException e) {
				System.out.println("Invalid jwt");
				sendJsonResponse(response, new CustomResponse(HttpStatus.UNAUTHORIZED.value(), null, "Invalid Token"), HttpStatus.UNAUTHORIZED);
				return;
			}

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			} else {
				System.out.println("Token not valid");
				sendJsonResponse(response, new CustomResponse(HttpStatus.UNAUTHORIZED.value(), null, "Token not valid"), HttpStatus.UNAUTHORIZED);
				return;
			}
		}
		else {
			System.out.println("Token Does not start with Bearer");
			sendJsonResponse(response, new CustomResponse(HttpStatus.UNAUTHORIZED.value(), null, "Token not found"), HttpStatus.UNAUTHORIZED);
			return;
		}
		System.out.println("request = "+request.getRequestURI());

		filterChain.doFilter(request, response);
	}
	
	private void sendJsonResponse(HttpServletResponse response, CustomResponse errorResponse, HttpStatus status)
			throws IOException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = objectMapperBuilder.build();

		response.setStatus(status.value());
		response.setContentType("application/json");
		response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
		response.getWriter().flush();

	}

}
