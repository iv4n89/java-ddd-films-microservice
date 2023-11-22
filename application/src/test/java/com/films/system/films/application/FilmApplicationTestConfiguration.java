package com.films.system.films.application;

import static org.mockito.Mockito.mock;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.films.system.films.domain.ports.input.service.FilmApplicationService;
import com.films.system.films.domain.ports.output.repository.FilmRepository;

@SpringBootApplication(scanBasePackages = "com.films.system.application")
public class FilmApplicationTestConfiguration {

}
