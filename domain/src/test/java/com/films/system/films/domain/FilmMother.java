package com.films.system.films.domain;

import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.films.application.dto.FilmCreateDto;
import com.films.system.films.domain.models.Film;
import com.films.system.films.domain.valueobjects.FilmLaunchDate;
import com.films.system.films.domain.valueobjects.FilmTitle;

import java.util.Date;
import java.util.UUID;

public class FilmMother {
  public static Film random() {
    return Film.builder()
        .id(FilmIdMother.random())
        .title(FilmTitleMother.random())
        .launchDate(FilmLaunchDateMother.random())
        .imageId(ImageIdMother.random())
        .build();
  }

  public static Film fromRequest(FilmCreateDto requestDto) {
    return Film.builder()
        .id(FilmIdMother.from(requestDto.getId().toString()))
        .title(FilmTitleMother.from(requestDto.getTitle()))
        .launchDate(FilmLaunchDateMother.from(requestDto.getLaunchDate()))
        .build();
  }

  public static Film from(UUID id, String title, Date launchDate) {
    return Film.builder()
        .id(new FilmId(id))
        .title(new FilmTitle(title))
        .launchDate(new FilmLaunchDate(launchDate))
        .build();
  }
}
