package com.films.system.films.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition
@Configuration
public class OpenApiConfiguration {
  @Bean
  OpenAPI api(
      @Value("${openapi.service.title}") String serviceTitle,
      @Value("${openapi.service.version}") String serviceVersion,
      @Value("${openapi.service.url}") String serviceUrl) {
    return new OpenAPI()
        .servers(List.of(new Server().url(serviceUrl)))
        .info(new Info().title(serviceTitle).version(serviceVersion));
  }
}
