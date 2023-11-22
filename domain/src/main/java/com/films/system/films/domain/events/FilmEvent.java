package com.films.system.films.domain.events;

import com.films.system.common.domain.events.DomainEvent;
import com.films.system.films.domain.models.Film;

import java.time.ZonedDateTime;

public abstract class FilmEvent implements DomainEvent<Film> {
  private final Film film;
  private final ZonedDateTime createdAt;

  protected FilmEvent(Film film, ZonedDateTime createdAt) {
    this.film = film;
    this.createdAt = createdAt;
  }

  public Film getFilm() {
    return film;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }
}
