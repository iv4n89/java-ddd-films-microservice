package com.films.system.films.domain.ports.output.repository;

import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.films.domain.models.Film;

import java.util.List;
import java.util.Optional;

public interface FilmRepository {
    void save(Film film);
    Optional<Film> findById(FilmId filmId);
    List<Film> findAll();
    void delete(FilmId filmId);
    void updateMeanRating(FilmId filmId, Integer meanRating);
}
