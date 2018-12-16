package com.ddvitos.app.comexportbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ddvitos.app.comexportbackend.security.AppProperties;

@SpringBootApplication
public class ComexportBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComexportBackendApplication.class, args);
	}

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}

}
