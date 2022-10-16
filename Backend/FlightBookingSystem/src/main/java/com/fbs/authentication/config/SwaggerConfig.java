//package com.fbs.authentication.config;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//public class SwaggerConfig {
//	public static final String AUTHORIZATION_HEADER = "Authorization";
//
//	private ApiKey apikeys() {
//		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//	}
//
//	private List<SecurityContext> securityContexts() {
//		return Arrays.asList(SecurityContext.builder().securityReferences(reference()).build());
//	}
//
//	private List<SecurityReference> reference() {
//		AuthorizationScope scopes = new AuthorizationScope("global", "accessEverything");
//
//		return Arrays.asList(new SecurityReference("scope", new AuthorizationScope[] { scopes }));
//	}
//
//	@Bean
//	public Docket api() {
//
//		return new Docket(DocumentationType.SWAGGER_2).securityContexts(securityContexts())
//				.securitySchemes(Arrays.asList(apikeys()));
//	}
//}
