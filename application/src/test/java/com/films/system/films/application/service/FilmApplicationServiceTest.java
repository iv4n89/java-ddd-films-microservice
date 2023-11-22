package com.films.system.films.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.films.system.common.domain.test.DateMother;
import com.films.system.common.domain.test.UuidMother;
import com.films.system.common.domain.test.WordMother;
import com.films.system.films.application.adapters.input.service.FilmApplicationServiceImpl;
import com.films.system.films.application.exceptions.FilmNotFoundException;
import com.films.system.films.domain.FilmCreatedEventMother;
import com.films.system.films.domain.FilmDeletedEventMother;
import com.films.system.films.domain.FilmMother;
import com.films.system.films.domain.events.FilmCreatedEvent;
import com.films.system.films.domain.events.FilmDeletedEvent;
import com.films.system.films.domain.models.Film;
import com.films.system.films.domain.ports.input.usecases.FilmCreator;
import com.films.system.films.domain.ports.input.usecases.FilmDeleter;
import com.films.system.films.domain.ports.input.usecases.FilmFinder;
import com.films.system.films.domain.ports.input.usecases.FilmImageAdder;
import com.films.system.films.domain.ports.output.message.publisher.FilmCreatedRequestMessagePublisher;
import com.films.system.films.domain.ports.output.message.publisher.FilmDeletedRequestMessagePublisher;
import com.films.system.films.domain.ports.output.message.publisher.FilmImageAddedMessagePublisher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilmApplicationServiceTest {

    private FilmApplicationServiceImpl filmApplicationService;

    @Mock
    private FilmCreator filmCreator;

    @Mock
    private FilmFinder filmFinder;

    @Mock
    private FilmDeleter filmDeleter;

    @Mock
    private FilmImageAdder filmImageAdder;

    @Mock
    private FilmImageAddedMessagePublisher filmImageAddedMessagePublisher;

    @Mock
    private FilmCreatedRequestMessagePublisher filmCreatedRequestMessagePublisher;

    @Mock
    private FilmDeletedRequestMessagePublisher filmDeletedRequestMessagePublisher;

    @BeforeAll
    void setUp() {
        filmApplicationService = new FilmApplicationServiceImpl(
                filmCreator,
                filmFinder,
                filmDeleter,
                filmImageAdder,
                filmCreatedRequestMessagePublisher,
                filmDeletedRequestMessagePublisher,
                filmImageAddedMessagePublisher
        );
    }

    @Test
    void testFindAllFilms() {
        List<Film> expected = List.of(FilmMother.random(), FilmMother.random(), FilmMother.random());
        when(filmFinder.findAll()).thenReturn(expected);

        List<Film> actual = filmApplicationService.findAll();
        verify(filmFinder, atLeastOnce()).findAll();

        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getFilmId(), actual.get(0).getFilmId());
        assertEquals(expected.get(1).getFilmId(), actual.get(1).getFilmId());
        assertEquals(expected.get(2).getFilmId(), actual.get(2).getFilmId());
    }

    @Test
    void testFindAllFilmsNoExistingFilms() {
        List<Film> expected = List.of();
        when(filmFinder.findAll()).thenReturn(expected);

        List<Film> actual = filmApplicationService.findAll();
        verify(filmFinder, atLeastOnce()).findAll();

        assertEquals(expected, actual);
        assertEquals(0, actual.size());
    }

    @Test
    void testFindOneExistingFilm() {
        Film expected = FilmMother.random();
        when(filmFinder.findById(any(UUID.class))).thenReturn(Optional.of(expected));

        Optional<Film> actual = filmApplicationService.findOne(expected.getFilmId().getValue());
        verify(filmFinder, atLeastOnce()).findById(any(UUID.class));

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
        assertEquals(expected.getFilmId(), actual.get().getFilmId());
    }

    @Test
    void testFindOneNonExistingFilm() {
        UUID filmId = UUID.randomUUID();
        when(filmFinder.findById(any(UUID.class))).thenReturn(Optional.empty());

        Optional<Film> actual = filmApplicationService.findOne(filmId);
        verify(filmFinder, atLeastOnce()).findById(any(UUID.class));

        assertFalse(actual.isPresent());
    }

    @Test
    void testCreateAFilm() {
        UUID expectedId = UuidMother.randomUUID();
        String expectedTitle = WordMother.random();
        Date expectedLaunchDate = DateMother.random();
        FilmCreatedEvent expectedEvent = FilmCreatedEventMother.from(expectedId, expectedTitle, expectedLaunchDate);
        when(filmCreator.create(any(UUID.class), any(String.class), any(Date.class))).thenReturn(expectedEvent);

        filmApplicationService.create(expectedId, expectedTitle, expectedLaunchDate);
        verify(filmCreator, atLeastOnce()).create(any(UUID.class), any(String.class), any(Date.class));
        verify(filmCreatedRequestMessagePublisher, times(1)).publish(expectedEvent);
    }

    @Test
    void testDeleteAFilm() {
        UUID expectedUuid = UuidMother.randomUUID();
        FilmDeletedEvent expectedEvent = FilmDeletedEventMother.from(expectedUuid);
        when(filmDeleter.delete(any(UUID.class))).thenReturn(expectedEvent);

        filmApplicationService.delete(expectedUuid);
        verify(filmDeleter, atLeastOnce()).delete(any(UUID.class));
        verify(filmDeletedRequestMessagePublisher, times(1)).publish(expectedEvent);
    }

    @Test
    void testDeleteANonExistingFilm() {
        UUID expectedUuid = UuidMother.randomUUID();
        when(filmDeleter.delete(expectedUuid)).thenThrow(FilmNotFoundException.class);

        assertThrows(FilmNotFoundException.class, () -> filmApplicationService.delete(expectedUuid));
        verify(filmDeleter, atLeastOnce()).delete(expectedUuid);
        verify(filmDeletedRequestMessagePublisher, times(0)).publish(any(FilmDeletedEvent.class));
    }

}
