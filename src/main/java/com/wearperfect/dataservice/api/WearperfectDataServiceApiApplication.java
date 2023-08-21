package com.wearperfect.dataservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wearperfect.dataservice.api.security.serviceImpl.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing(
        auditorAwareRef = "auditorAware",
        modifyOnCreate = false
)
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.wearperfect.dataservice.api.repository"},
        repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class
)
public class WearperfectDataServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WearperfectDataServiceApiApplication.class, args);
    }

    @Bean
    public ObjectMapper defaultMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public JavaTimeModule dateTimeModule() {
        return new JavaTimeModule();
    }

    @Bean
    public AuditorAware<Long> auditorAware() {
        return new AuditorAwareImpl();
    }
}
