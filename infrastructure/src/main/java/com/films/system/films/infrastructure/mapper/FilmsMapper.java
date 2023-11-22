package com.films.system.films.infrastructure.mapper;

import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.common.domain.valueobject.ImageId;
import com.films.system.films.application.dto.FilmResponseDto;
import com.films.system.films.domain.models.Film;
import com.films.system.films.domain.valueobjects.FilmLaunchDate;
import com.films.system.films.domain.valueobjects.FilmMeanRating;
import com.films.system.films.domain.valueobjects.FilmTitle;
import com.films.system.films.infrastructure.entity.FilmEntity;
import org.springframework.stereotype.Component;

@Component
public class FilmsMapper {

  public Film filmEntityToFilm(FilmEntity filmEntity) {
    return Film.builder()
        .id(new FilmId(filmEntity.getId()))
        .title(new FilmTitle(filmEntity.getTitle()))
        .launchDate(new FilmLaunchDate(filmEntity.getLaunchDate()))
        .meanRating(new FilmMeanRating(filmEntity.getMeanRating()))
        .imageId(filmEntity.getImageId() != null ? new ImageId(filmEntity.getImageId()) : null)
        .build();
  }

  public FilmEntity filmToFilmEntity(Film film) {
    return FilmEntity.builder()
        .id(film.getFilmId().getValue())
        .title(film.getFilmTitle().getValue())
        .launchDate(film.getLaunchDate().getValue())
        .meanRating(film.getMeanRating() != null ? film.getMeanRating().getValue() : 0)
        .imageId(film.getImageId() != null ? film.getImageId().getValue() : null)
        .build();
  }

  public FilmResponseDto filmToResponseDto(Film film) {
    return FilmResponseDto.builder()
        .id(film.getFilmId().getValue())
        .title(film.getFilmTitle().getValue())
        .launchDate(film.getLaunchDate().getValue())
        .meanRating(film.getMeanRating().getValue())
        .imageId(film.getImageId() != null ? film.getImageId().getValue() : null)
        .build();
  }
}
