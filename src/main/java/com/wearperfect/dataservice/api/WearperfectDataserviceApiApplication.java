package com.wearperfect.dataservice.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {
		"com.wearperfect.dataservice.api"
})
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {
        "com.wearperfect.dataservice.api.repositories"
})
@EnableTransactionManagement
public class WearperfectDataserviceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WearperfectDataserviceApiApplication.class, args);
	}
}
