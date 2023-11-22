package com.films.system.films.domain;

import com.films.system.common.domain.test.DateMother;
import com.films.system.films.domain.valueobjects.FilmLaunchDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilmLaunchDateMother {

  private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  public static FilmLaunchDate random() {
    return new FilmLaunchDate(DateMother.random());
  }

  public static FilmLaunchDate from(String value) throws ParseException {
    return new FilmLaunchDate(formatter.parse(value));
  }

  public static FilmLaunchDate from(Date value) {
    return new FilmLaunchDate(value);
  }
}
