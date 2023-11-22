package com.films.system.films.domain.ports.input.usecases;

import com.films.system.films.domain.models.Film;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FilmFinder {
    Optional<Film> findById(UUID filmId);
    List<Film> findAll();
}
