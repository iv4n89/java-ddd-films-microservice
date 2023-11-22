package com.films.system.films.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.films.system.films.application.FilmApplicationTestConfiguration;
import com.films.system.films.domain.FilmMother;
import com.films.system.films.domain.models.Film;
import com.films.system.films.domain.ports.input.usecases.FilmFinder;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = FilmApplicationTestConfiguration.class)
class FilmFinderTest {

    @Mock
    private FilmFinder filmFinder;

    @Test
    void testFindAllFilms() {
        List<Film> expected = List.of(FilmMother.random(), FilmMother.random(), FilmMother.random());
        when(filmFinder.findAll()).thenReturn(expected);

        List<Film> actual = filmFinder.findAll();

        verify(filmFinder, atLeastOnce()).findAll();

        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getFilmId(), actual.get(0).getFilmId());
        assertEquals(expected.get(1).getFilmId(), actual.get(1).getFilmId());
        assertEquals(expected.get(2).getFilmId(), actual.get(2).getFilmId());
    }

    @Test
    void testFindOneExistingFilm() {
        Film expected = FilmMother.random();
        when(filmFinder.findById(any(UUID.class))).thenReturn(Optional.of(expected));

        Optional<Film> actual = filmFinder.findById(expected.getFilmId().getValue());

        verify(filmFinder, atLeastOnce()).findById(any(UUID.class));

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
        assertEquals(expected.getFilmId(), actual.get().getFilmId());
    }

    @Test
    void testFindOneNonExistingFilm() {
        when(filmFinder.findById(any(UUID.class))).thenReturn(Optional.empty());

        Optional<Film> actual = filmFinder.findById(UUID.randomUUID());

        verify(filmFinder, atLeastOnce()).findById(any(UUID.class));

        assertFalse(actual.isPresent());
    }

}
