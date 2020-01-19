package com.proiect;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//folosita pentru a configura filtrul astfel incat nimeni care nu e logat sa nu poata accesa aplicatia
@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter() {
		FilterRegistrationBean<LoginFilter> registration = new FilterRegistrationBean<LoginFilter>();
		registration.setFilter(new LoginFilter());
		registration.addUrlPatterns("/secured/*");
		return registration;
	}
}
