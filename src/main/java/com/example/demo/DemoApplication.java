package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public Docket swaggerConfiguration(){
		/**
		 * Docket customizes you swagger
		 */

		//Builder pattern
		return new Docket(DocumentationType.SWAGGER_2)
				/**
				 * ApiSelectorBuilder
				 * Lets you customize how you want swagger to behave
				 */
				.select()
				/**
				 * Url paths
				 */
				.paths(PathSelectors.ant("/api/*"))
				/**
				 * Specifying packages to look at
				 */
				.apis(RequestHandlerSelectors.basePackage("com.example"))
				.build()
				//more info
				.apiInfo(apiDetails());
	}

	private ApiInfo	apiDetails(){
		return new ApiInfo(
				"Person Controller",
				"Simple API",
				"1.0",
				"Free to use",
				new Contact("Samuel Molcan", "localhost:8080/v2/api-docs", "sammdev@gmail.com"),
				"API LICENSE",
				"localhost:8080/v2/api-docs",
				Collections.emptyList()
		);
	}

}
