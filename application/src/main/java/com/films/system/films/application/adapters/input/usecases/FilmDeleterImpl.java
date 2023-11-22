package com.films.system.films.application.adapters.input.usecases;

import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.films.application.exceptions.FilmNotFoundException;
import com.films.system.films.domain.events.FilmDeletedEvent;
import com.films.system.films.domain.models.Film;
import com.films.system.films.domain.ports.input.usecases.FilmDeleter;
import com.films.system.films.domain.ports.output.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class FilmDeleterImpl implements FilmDeleter {

  private final FilmRepository filmRepository;

  @Override
  public FilmDeletedEvent delete(UUID filmID) {
    Optional<Film> film = filmRepository.findById(new FilmId(filmID));
    if (film.isEmpty()) {
      throw new FilmNotFoundException("Film with id: " + filmID.toString() + " can not be found");
    }
    filmRepository.delete(new FilmId(filmID));
    return new FilmDeletedEvent(film.get(), ZonedDateTime.now(ZoneId.of("UTC")));
  }
}
