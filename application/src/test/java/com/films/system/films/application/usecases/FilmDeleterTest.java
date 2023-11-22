package com.films.system.films.application.usecases;

import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import com.films.system.common.domain.test.UuidMother;
import com.films.system.films.application.FilmApplicationTestConfiguration;
import com.films.system.films.domain.ports.input.usecases.FilmDeleter;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = FilmApplicationTestConfiguration.class)
class FilmDeleterTest {

    private FilmDeleter filmDeleter;

    @BeforeAll
    void setUp() {
        filmDeleter = mock(FilmDeleter.class);
    }

    @Test
    void testDeleteFilm() {
        filmDeleter.delete(UuidMother.randomUUID());
        verify(filmDeleter, atLeastOnce()).delete(any(UUID.class));
    }
    
}
