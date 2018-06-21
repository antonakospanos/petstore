package com.eurodyn.hr.petstore.web.configuration;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

//	@Bean(name = "Admin API")
//	public Docket adminApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("Admin API")
//				.apiInfo(adminInfo())
//				.select()
//				.paths(regex(".*/(admin).*"))
//				.build();
//	}
//
//	private ApiInfo adminInfo() {
//		return new ApiInfoBuilder()
//				.title("Admin API")
//				.contact("petstore")
//				.description("Pet Store Administration Interface ")
//				.build();
//	}

	@Bean(name = "Pet Store API")
	public Docket petStoreAPIv1() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Pet Store API")
				.apiInfo(petStoreInfo())
				.select()
				.paths(apiV1Paths())
				.build();
	}

	@Bean(name = "Pet Store API v2")
	public Docket petStoreAPIv2() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Pet Store API v2")
				.apiInfo(petStoreInfo())
				.select()
				.paths(apiV2Paths())
				.build();
	}

	private ApiInfo petStoreInfo() {
		return new ApiInfoBuilder()
				.title("Pet Store API")
				.contact("www.eurodyn.com")
				.description("The Online Pet Store")
				.build();
	}

	private Predicate<String> apiV1Paths() {
		return or(regex("/(users|pets|sales).*"));
	}

	private Predicate<String> apiV2Paths() {
		return or(regex("/v2/.*"));
	}
}