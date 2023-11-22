package com.films.system.films.application.adapters.input.service;

import com.films.system.films.domain.events.FilmCreatedEvent;
import com.films.system.films.domain.events.FilmDeletedEvent;
import com.films.system.films.domain.events.FilmImageAddedEvent;
import com.films.system.films.domain.models.Film;
import com.films.system.films.domain.ports.input.service.FilmApplicationService;
import com.films.system.films.domain.ports.input.usecases.FilmCreator;
import com.films.system.films.domain.ports.input.usecases.FilmDeleter;
import com.films.system.films.domain.ports.input.usecases.FilmFinder;
import com.films.system.films.domain.ports.input.usecases.FilmImageAdder;
import com.films.system.films.domain.ports.output.message.publisher.FilmCreatedRequestMessagePublisher;
import com.films.system.films.domain.ports.output.message.publisher.FilmDeletedRequestMessagePublisher;
import com.films.system.films.domain.ports.output.message.publisher.FilmImageAddedMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilmApplicationServiceImpl implements FilmApplicationService {

  private final FilmCreator filmCreator;
  private final FilmFinder filmFinder;
  private final FilmDeleter filmDeleter;
  private final FilmImageAdder filmImageAdder;
  private final FilmCreatedRequestMessagePublisher filmCreatedRequestMessagePublisher;
  private final FilmDeletedRequestMessagePublisher filmDeletedRequestMessagePublisher;
  private final FilmImageAddedMessagePublisher filmImageAddedMessagePublisher;

  @Override
  public void create(UUID filmId, String filmTitle, Date filmLaunchDate) {
    FilmCreatedEvent event = filmCreator.create(filmId, filmTitle, filmLaunchDate);
    filmCreatedRequestMessagePublisher.publish(event);
  }

  @Override
  public Optional<Film> findOne(UUID id) {
    return filmFinder.findById(id);
  }

  @Override
  public List<Film> findAll() {
    return filmFinder.findAll();
  }

  @Override
  public void delete(UUID id) {
    FilmDeletedEvent event = filmDeleter.delete(id);
    filmDeletedRequestMessagePublisher.publish(event);
  }

  @Override
  public void addImage(UUID filmId, UUID imageId) {
    filmImageAdder.addImage(filmId, imageId);
  }

  @Override
  public void uploadImage(UUID filmId, MultipartFile file) {
    FilmImageAddedEvent event = filmImageAdder.uploadImage(filmId, file);
    filmImageAddedMessagePublisher.publish(event);
  }
}
