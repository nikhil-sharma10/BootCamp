package com.example.RestFulServicesExercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestFulServicesExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestFulServicesExerciseApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver(){
		AcceptHeaderLocaleResolver
				sessionLocaleResolver = new AcceptHeaderLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);
		return sessionLocaleResolver;
	}


}
