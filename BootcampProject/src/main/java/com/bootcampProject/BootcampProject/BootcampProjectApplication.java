package com.bootcampProject.BootcampProject;

import com.bootcampProject.BootcampProject.auditor.AuditoAwareImpl;
import com.bootcampProject.BootcampProject.domain.ImageTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableConfigurationProperties({ImageTable.class})
public class BootcampProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootcampProjectApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditoAwareImpl();
	}

}
