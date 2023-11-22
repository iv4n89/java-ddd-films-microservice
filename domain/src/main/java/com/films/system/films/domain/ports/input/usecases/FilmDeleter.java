package com.films.system.films.domain.ports.input.usecases;

import com.films.system.films.domain.events.FilmDeletedEvent;

import java.util.UUID;

public interface FilmDeleter {
    FilmDeletedEvent delete(UUID filmID);
}
