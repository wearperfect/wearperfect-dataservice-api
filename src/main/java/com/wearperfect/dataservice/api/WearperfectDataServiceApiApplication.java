package com.wearperfect.dataservice.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.wearperfect.dataservice.api" })
@EnableJpaRepositories(basePackages = { "com.wearperfect.dataservice.api.repository" })
public class WearperfectDataServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WearperfectDataServiceApiApplication.class, args);
	}
}
