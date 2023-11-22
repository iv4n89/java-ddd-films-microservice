package com.films.system.films.domain;

import com.films.system.common.domain.test.WordMother;
import com.films.system.films.domain.events.FilmDeletedEvent;
import com.films.system.films.domain.models.Film;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

public class FilmDeletedEventMother {
  public static FilmDeletedEvent random() {
    return new FilmDeletedEvent(FilmMother.random(), ZonedDateTime.now(ZoneId.of("UTC")));
  }

  public static FilmDeletedEvent from(Film film) {
    return new FilmDeletedEvent(film, ZonedDateTime.now(ZoneId.of("UTC")));
  }

  public static FilmDeletedEvent from(UUID id) {
    Film film = FilmMother.from(id, WordMother.random(), new Date());
    return new FilmDeletedEvent(film, ZonedDateTime.now(ZoneId.of("UTC")));
  }
}
