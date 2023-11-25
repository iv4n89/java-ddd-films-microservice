package com.films.system.films.infrastructure.entity;

import com.films.system.common.domain.test.DateMother;
import com.films.system.common.domain.test.UuidMother;
import com.films.system.common.domain.test.WordMother;
import com.films.system.films.domain.models.Film;

public class FilmEntityMother {
  public static FilmEntity random() {
    return FilmEntity.builder()
        .id(UuidMother.randomUUID())
        .title(WordMother.random())
        .launchDate(DateMother.random())
        .build();
  }

  public static FilmEntity fromFilm(Film film) {
    return FilmEntity.builder()
        .id(film.getFilmId().getValue())
        .title(film.getFilmTitle().getValue())
        .launchDate(film.getLaunchDate().getValue())
        .build();
  }
}
