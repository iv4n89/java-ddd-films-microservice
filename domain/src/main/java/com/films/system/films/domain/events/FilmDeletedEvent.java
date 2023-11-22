package com.films.system.films.domain.events;

import com.films.system.films.domain.models.Film;

import java.time.ZonedDateTime;

public class FilmDeletedEvent extends FilmEvent {
  public FilmDeletedEvent(Film film, ZonedDateTime createdAt) {
    super(film, createdAt);
  }
}
