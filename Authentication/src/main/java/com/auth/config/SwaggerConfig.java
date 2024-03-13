package com.auth.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
public static final String AUTHORIZATION_HEADER="Authorization";
	
	private ApiKey apiKey()
	{
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	
	private List<SecurityContext> securityContexts()
	{
		return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
	}
	
	private List<SecurityReference> securityReferences()
	{
		AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
		
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
	}

	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo getInfo() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				.title("Authentication: Backend.")
				.description("Authentication reference for developer")
				.version("1.0.0")
				.build();
//		return new ApiInfo("Authentication: Backend.",
//				"Authentication reference for developer",
//				"1.0.1",
//				null,
//				null,
//				"License of APIS",
//				"API license URL", 
//				Collections.emptyList());
	}

}
