package com.example.kata.config;

import com.example.kata.domain.application.ReleveCompteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public ReleveCompteService releveCompteService() {
        return new ReleveCompteService();
    }
}
