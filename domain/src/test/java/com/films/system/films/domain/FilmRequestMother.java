package com.films.system.films.domain;

import com.films.system.common.domain.test.DateMother;
import com.films.system.common.domain.test.UuidMother;
import com.films.system.common.domain.test.WordMother;
import com.films.system.films.application.dto.FilmCreateDto;

public class FilmRequestMother {
  public static FilmCreateDto random() {
    return new FilmCreateDto(UuidMother.randomUUID(), WordMother.random(), DateMother.random());
  }
}
