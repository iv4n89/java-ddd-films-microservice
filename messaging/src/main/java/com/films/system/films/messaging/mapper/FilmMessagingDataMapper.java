package com.films.system.films.messaging.mapper;

import com.films.system.common.application.dto.message.FilmMessageDto;
import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.common.domain.valueobject.ImageId;
import com.films.system.films.domain.models.Film;
import com.films.system.films.domain.valueobjects.FilmLaunchDate;
import com.films.system.films.domain.valueobjects.FilmMeanRating;
import com.films.system.films.domain.valueobjects.FilmTitle;
import org.springframework.stereotype.Component;

@Component
public class FilmMessagingDataMapper {

  public FilmMessageDto filmToFilmMessageDto( Film film) {
    return FilmMessageDto.builder()
        .id(film.getFilmId().getValue())
        .title(film.getFilmTitle().getValue())
        .launchDate(film.getLaunchDate().getValue())
        .meanRating(film.getMeanRating() != null ? film.getMeanRating().getValue() : 0)
        .imageId(film.getImageId() != null ? film.getImageId().getValue() : null)
        .build();
  }

  public Film filmMessageDtoToFilm(FilmMessageDto filmCreatedDto) {
    return Film.builder()
        .id(new FilmId(filmCreatedDto.getId()))
        .title(new FilmTitle(filmCreatedDto.getTitle()))
        .launchDate(new FilmLaunchDate(filmCreatedDto.getLaunchDate()))
        .meanRating(
            new FilmMeanRating(
                filmCreatedDto.getMeanRating() != null ? filmCreatedDto.getMeanRating() : 0))
        .imageId(
            filmCreatedDto.getImageId() != null ? new ImageId(filmCreatedDto.getImageId()) : null)
        .build();
  }
}
