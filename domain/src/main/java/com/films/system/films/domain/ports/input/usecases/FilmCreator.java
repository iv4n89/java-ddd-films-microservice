package com.films.system.films.domain.ports.input.usecases;

import com.films.system.films.domain.events.FilmCreatedEvent;

import java.util.Date;
import java.util.UUID;

public interface FilmCreator {
    FilmCreatedEvent create(UUID filmId, String filmTitle, Date filmLaunchDate);
}
