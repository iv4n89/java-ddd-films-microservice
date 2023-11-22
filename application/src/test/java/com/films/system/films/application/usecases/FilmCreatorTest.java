package com.films.system.films.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.films.system.films.application.FilmApplicationTestConfiguration;
import com.films.system.films.domain.FilmCreatedEventMother;
import com.films.system.films.domain.FilmMother;
import com.films.system.films.domain.events.FilmCreatedEvent;
import com.films.system.films.domain.models.Film;
import com.films.system.films.domain.ports.input.usecases.FilmCreator;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = FilmApplicationTestConfiguration.class)
class FilmCreatorTest {

    @Mock
    private FilmCreator filmCreator;

    @Test
    void testCreateFilm() {
        Film expected = FilmMother.random();
        FilmCreatedEvent expectedEvent = FilmCreatedEventMother.from(expected);
        when(filmCreator.create(any(UUID.class), anyString(), any(Date.class))).thenReturn(expectedEvent);

        FilmCreatedEvent actualEvent = filmCreator.create(expected.getFilmId().getValue(), expected.getFilmTitle().getValue(), expected.getLaunchDate().getValue());

        verify(filmCreator, times(1)).create(any(UUID.class), anyString(), any(Date.class));

        assertEquals(expectedEvent, actualEvent);
        assertEquals(expected.getFilmId(), actualEvent.getFilm().getFilmId());
    }

}
