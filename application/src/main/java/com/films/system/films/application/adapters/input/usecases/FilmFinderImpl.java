package com.films.system.films.application.adapters.input.usecases;

import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.films.domain.models.Film;
import com.films.system.films.domain.ports.input.usecases.FilmFinder;
import com.films.system.films.domain.ports.output.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class FilmFinderImpl implements FilmFinder {

    private final FilmRepository filmRepository;

    @Override
    public Optional<Film> findById(UUID filmId) {
        return filmRepository.findById(new FilmId(filmId));
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }
}
