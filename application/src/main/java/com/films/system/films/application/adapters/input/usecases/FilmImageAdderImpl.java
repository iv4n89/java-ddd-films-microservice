package com.films.system.films.application.adapters.input.usecases;

import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.common.domain.valueobject.ImageId;
import com.films.system.films.application.exceptions.FilmNotFoundException;
import com.films.system.films.domain.events.FilmImageAddedEvent;
import com.films.system.films.domain.models.Film;
import com.films.system.films.domain.ports.input.usecases.FilmImageAdder;
import com.films.system.films.domain.ports.output.message.publisher.FilmImageAddedMessagePublisher;
import com.films.system.films.domain.ports.output.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class FilmImageAdderImpl implements FilmImageAdder {

  private final FilmRepository filmRepository;
  private final FilmImageAddedMessagePublisher filmImageAddedMessagePublisher;

  @Override
  public void addImage(UUID filmId, UUID imageId) {
    Optional<Film> optionalFilm = filmRepository.findById(new FilmId(filmId));
    if (optionalFilm.isEmpty()) {
      throw new FilmNotFoundException("Film wiht id: " + filmId.toString() + " could not be found");
    }
    Film film = optionalFilm.get();
    film.setImageId(new ImageId(imageId));
    filmRepository.save(film);
  }

  @Override
  public FilmImageAddedEvent uploadImage(UUID filmId, MultipartFile file) {
    UUID imageId = UUID.randomUUID();
    addImage(filmId, imageId);
    return new FilmImageAddedEvent(imageId, file);
  }
}
