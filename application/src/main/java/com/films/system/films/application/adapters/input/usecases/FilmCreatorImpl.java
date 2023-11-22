package com.films.system.films.application.adapters.input.usecases;

import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.films.domain.events.FilmCreatedEvent;
import com.films.system.films.domain.models.Film;
import com.films.system.films.domain.ports.input.usecases.FilmCreator;
import com.films.system.films.domain.ports.output.repository.FilmRepository;
import com.films.system.films.domain.valueobjects.FilmLaunchDate;
import com.films.system.films.domain.valueobjects.FilmTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public final class FilmCreatorImpl implements FilmCreator {

  private final FilmRepository filmRepository;

  @Override
  public FilmCreatedEvent create(UUID filmId, String filmTitle, Date filmLaunchDate) {
    Film film =
        Film.builder()
            .id(new FilmId(filmId))
            .title(new FilmTitle(filmTitle))
            .launchDate(new FilmLaunchDate(filmLaunchDate))
            .build();

    filmRepository.save(film);
    return new FilmCreatedEvent(film, ZonedDateTime.now(ZoneId.of("UTC")));
  }
}
