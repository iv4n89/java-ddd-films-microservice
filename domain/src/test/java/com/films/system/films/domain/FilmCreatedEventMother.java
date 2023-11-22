package com.films.system.films.domain;

import com.films.system.films.domain.events.FilmCreatedEvent;
import com.films.system.films.domain.models.Film;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

public class FilmCreatedEventMother {
  public static FilmCreatedEvent random() {
    return new FilmCreatedEvent(FilmMother.random(), ZonedDateTime.now(ZoneId.of("UTC")));
  }

  public static FilmCreatedEvent from(Film film) {
    return new FilmCreatedEvent(film, ZonedDateTime.now(ZoneId.of("UTC")));
  }

  public static FilmCreatedEvent from(UUID id, String title, Date launchDate) {
    return new FilmCreatedEvent(
        FilmMother.from(id, title, launchDate), ZonedDateTime.now(ZoneId.of("UTC")));
  }
}
