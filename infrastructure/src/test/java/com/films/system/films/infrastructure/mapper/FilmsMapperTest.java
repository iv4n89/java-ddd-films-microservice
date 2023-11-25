package com.films.system.films.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.films.system.films.domain.FilmMother;
import com.films.system.films.domain.models.Film;
import com.films.system.films.infrastructure.FilmInfrastructureTestConfiguration;
import com.films.system.films.infrastructure.entity.FilmEntity;
import com.films.system.films.infrastructure.entity.FilmEntityMother;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilmsMapperTest {

    private FilmsMapper filmsMapper;

    @BeforeAll
    void setUp() {
        filmsMapper = new FilmsMapper();
    }

    @Test
    void testFilmEntityToFilm() {
        FilmEntity filmEntity = FilmEntityMother.random();
        Film film = filmsMapper.filmEntityToFilm(filmEntity);

        assertEquals(filmEntity.getId(), film.getFilmId().getValue());
        assertEquals(filmEntity.getTitle(), film.getFilmTitle().getValue());
        assertEquals(filmEntity.getLaunchDate(), film.getLaunchDate().getValue());
    }

    @Test
    void testFilmToFilmEntity() {
        Film film = FilmMother.random();
        FilmEntity filmEntity = filmsMapper.filmToFilmEntity(film);

        assertEquals(film.getFilmId().getValue(), filmEntity.getId());
        assertEquals(film.getFilmTitle().getValue(), filmEntity.getTitle());
        assertEquals(film.getLaunchDate().getValue(), filmEntity.getLaunchDate());
    }

}
