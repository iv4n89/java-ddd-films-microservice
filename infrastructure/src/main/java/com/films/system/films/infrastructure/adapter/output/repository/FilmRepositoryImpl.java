package com.films.system.films.infrastructure.adapter.output.repository;

import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.films.application.exceptions.FilmNotFoundException;
import com.films.system.films.domain.models.Film;
import com.films.system.films.domain.ports.output.repository.FilmRepository;
import com.films.system.films.domain.valueobjects.FilmMeanRating;
import com.films.system.films.infrastructure.entity.FilmEntity;
import com.films.system.films.infrastructure.mapper.FilmsMapper;
import com.films.system.films.infrastructure.repository.FilmJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FilmRepositoryImpl implements FilmRepository {

  private final FilmJpaRepository filmJpaRepository;
  private final FilmsMapper filmsMapper;

  @Override
  @Transactional
  public void save(Film film) {
    FilmEntity filmEntity = filmsMapper.filmToFilmEntity(film);
    filmJpaRepository.save(filmEntity);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Film> findById(FilmId filmId) {
    return filmJpaRepository.findById(filmId.getValue()).map(filmsMapper::filmEntityToFilm);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Film> findAll() {
    return filmJpaRepository.findAll().stream().map(filmsMapper::filmEntityToFilm).toList();
  }

  @Override
  @Transactional
  public void delete(FilmId filmId) {
    if (!filmJpaRepository.existsById(filmId.getValue())) {
      throw new FilmNotFoundException(
          "Film with id: " + filmId.getValue().toString() + " could not be found");
    }
    filmJpaRepository.deleteById(filmId.getValue());
  }

  @Override
  @Transactional
  public void updateMeanRating(FilmId filmId, Integer meanRating) {
    Film film =
        filmJpaRepository
            .findById(filmId.getValue())
            .map(filmsMapper::filmEntityToFilm)
            .orElseThrow();
    film.setMeanRating(new FilmMeanRating(meanRating));
    filmJpaRepository.save(filmsMapper.filmToFilmEntity(film));
  }
}
