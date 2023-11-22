package com.films.system.films.domain;

import com.films.system.common.domain.test.WordMother;
import com.films.system.films.domain.valueobjects.FilmTitle;

public class FilmTitleMother {
  public static FilmTitle random() {
    return new FilmTitle(WordMother.random());
  }

  public static FilmTitle from(String value) {
    return new FilmTitle(value);
  }
}
