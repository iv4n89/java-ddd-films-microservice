package com.films.system.films.infrastructure.adapter.output.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.films.application.exceptions.FilmNotFoundException;
import com.films.system.films.domain.FilmMother;
import com.films.system.films.domain.models.Film;
import com.films.system.films.infrastructure.entity.FilmEntity;
import com.films.system.films.infrastructure.entity.FilmEntityMother;
import com.films.system.films.infrastructure.mapper.FilmsMapper;
import com.films.system.films.infrastructure.repository.FilmJpaRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@Tag("FilmRepository")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilmRepositoryTest {

  private FilmJpaRepository filmJpaRepository;

  private FilmsMapper filmsMapper;

  private FilmRepositoryImpl filmRepository;

  @BeforeEach
  public void init() {
    filmJpaRepository = mock(FilmJpaRepository.class);
    filmsMapper = mock(FilmsMapper.class);
    filmRepository = new FilmRepositoryImpl(filmJpaRepository, filmsMapper);
  }

  @Test
  @Order(7)
  void testSave() {
    Film film = FilmMother.random();
    FilmEntity expected = FilmEntityMother.fromFilm(film);

    when(filmsMapper.filmToFilmEntity(film)).thenReturn(expected);
    when(filmJpaRepository.save(expected)).thenReturn(expected);

    filmRepository.save(film);

    verify(filmsMapper, atMostOnce()).filmToFilmEntity(film);
    verify(filmJpaRepository, atMostOnce()).save(expected);
  }

  @Test
  @Order(6)
  void testFindById() {
    Film film = FilmMother.random();
    FilmEntity expected = FilmEntityMother.fromFilm(film);

    when(filmJpaRepository.findById(any())).thenReturn(Optional.of(expected));
    when(filmsMapper.filmEntityToFilm(any())).thenReturn(film);

    filmRepository.findById(film.getFilmId());

    verify(filmJpaRepository, atMostOnce()).findById(expected.getId());
    verify(filmsMapper, atMostOnce()).filmEntityToFilm(expected);
  }

  @Test
  @Order(1)
  void testFindByIdNotFound() {
    Film film = FilmMother.random();
    FilmEntity expected = FilmEntityMother.fromFilm(film);

    when(filmJpaRepository.findById(film.getFilmId().getValue())).thenReturn(Optional.empty());

    Optional<Film> actual = filmRepository.findById(film.getFilmId());

    verify(filmJpaRepository, only()).findById(expected.getId());
    verify(filmsMapper, never()).filmEntityToFilm(expected);

    assertTrue(actual.isEmpty());
  }

  @Test
  @Order(5)
  void testFindAll() {
    Film film = FilmMother.random();
    FilmEntity expected = FilmEntityMother.fromFilm(film);

    when(filmJpaRepository.findAll()).thenReturn(List.of(expected));
    when(filmsMapper.filmEntityToFilm(expected)).thenReturn(film);

    filmRepository.findAll();

    verify(filmJpaRepository, atMostOnce()).findAll();
    verify(filmsMapper, atMostOnce()).filmEntityToFilm(expected);

    assertEquals(1, filmRepository.findAll().size());
    assertTrue(filmRepository.findAll().contains(film));
  }

  @Test
  @Order(2)
  void testFindAllEmpty() {
    when(filmJpaRepository.findAll()).thenReturn(List.of());
    assertTrue(filmRepository.findAll().isEmpty());
    verify(filmJpaRepository, atMostOnce()).findAll();
  }

  @Test
  @Order(9)
  void testDelete() {
    Film film = FilmMother.random();
    FilmEntity expected = FilmEntityMother.fromFilm(film);

    when(filmJpaRepository.existsById(film.getFilmId().getValue())).thenReturn(true);

    filmRepository.delete(film.getFilmId());

    verify(filmJpaRepository, atMostOnce()).existsById(expected.getId());
    verify(filmJpaRepository, atMostOnce()).deleteById(expected.getId());
  }

  @Test
  @Order(3)
  void testDeleteNotFound() {
    Film film = FilmMother.random();
    FilmEntity expected = FilmEntityMother.fromFilm(film);

    when(filmJpaRepository.existsById(film.getFilmId().getValue())).thenReturn(false);

    FilmId filmId = film.getFilmId();

    assertThrows(FilmNotFoundException.class, () -> filmRepository.delete(filmId));

    verify(filmJpaRepository, atMostOnce()).existsById(expected.getId());
    verify(filmJpaRepository, never()).deleteById(expected.getId());
  }

  @Test
  @Order(8)
  void testUpdateMeanRating() {
    Film film = FilmMother.random();
    FilmEntity expected = FilmEntityMother.fromFilm(film);

    when(filmJpaRepository.findById(film.getFilmId().getValue())).thenReturn(Optional.of(expected));
    when(filmsMapper.filmEntityToFilm(expected)).thenReturn(film);
    when(filmsMapper.filmToFilmEntity(film)).thenReturn(expected);
    when(filmJpaRepository.save(expected)).thenReturn(expected);

    filmRepository.updateMeanRating(film.getFilmId(), 5);

    verify(filmJpaRepository, atMostOnce()).findById(expected.getId());
    verify(filmsMapper, atMostOnce()).filmEntityToFilm(expected);
    verify(filmsMapper, atMostOnce()).filmToFilmEntity(film);
    verify(filmJpaRepository, atMostOnce()).save(expected);
  }

  @Test
  @Order(4)
  void testUpdateMeanRatingNotFound() {
    Film film = FilmMother.random();
    FilmEntity expected = FilmEntityMother.fromFilm(film);

    when(filmJpaRepository.findById(any())).thenReturn(Optional.empty());

    FilmId filmId = film.getFilmId();

    assertThrows(FilmNotFoundException.class, () -> filmRepository.updateMeanRating(filmId, 5));

    verify(filmJpaRepository, only()).findById(expected.getId());
    verify(filmsMapper, never()).filmEntityToFilm(expected);
    verify(filmsMapper, never()).filmToFilmEntity(film);
    verify(filmJpaRepository, never()).save(expected);
  }
}
