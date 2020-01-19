package com.proiect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

//clasa de unde porneste aplicatia
@SpringBootApplication
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@EnableScheduling
@EntityScan(basePackages = { "com.client", "com.rating", "com.statie", "com.traseu", "com.user", "com.utils",
		"com.cursa", "com.firma", "com.rezervare", "com.factura" })
@EnableJpaRepositories(basePackages = "com.repos")
@ComponentScan(basePackages = { "com.services", "com.controllers", "com.proiect", "com.repository" })
@ServletComponentScan
public class ProiectApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProiectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProiectApplication.class, args);
	}

}
