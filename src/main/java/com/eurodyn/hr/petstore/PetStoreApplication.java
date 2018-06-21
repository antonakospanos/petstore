package com.eurodyn.hr.petstore;

import com.google.common.collect.ImmutableMap;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration//(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
@ComponentScan(basePackages = {"com.eurodyn.hr.petstore"})
public class PetStoreApplication extends SpringBootServletInitializer {

	private static final String CONFIG_NAME = "petstore-application";

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.properties(ImmutableMap.of("spring.config.name", CONFIG_NAME));
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(PetStoreApplication.class).
				properties(ImmutableMap.of("spring.config.name", CONFIG_NAME))
				.build()
				.run(args);
	}
}
