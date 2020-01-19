package com.proiect;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//folosita pentru a configura filtrul care nu permite acces clientului la functionalitati pentru firme
@Configuration
public class RolesConfig {
	@Bean
	public FilterRegistrationBean<RolesFilter> rolesFilter() {
		FilterRegistrationBean<RolesFilter> registration = new FilterRegistrationBean<RolesFilter>();
		registration.setFilter(new RolesFilter());
		registration.addUrlPatterns("/secured/adaugaCursa");
		registration.addUrlPatterns("/secured/addTraseu");
		return registration;
	}

}
