package com.films.system.films;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = { "com.films.system.films.infrastructure" })
@EntityScan(basePackages = { "com.films.system.films.infrastructure" })
@SpringBootApplication(scanBasePackages = "com.films.system")
public class FilmsServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(FilmsServiceApplication.class, args);
    }
}
