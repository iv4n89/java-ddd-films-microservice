package com.films.system.films.domain.events;

import com.films.system.common.domain.events.DomainEvent;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class FilmImageAddedEvent implements DomainEvent<MultipartFile> {
  private final UUID imageId;
  private final MultipartFile file;

  public FilmImageAddedEvent(UUID imageId, MultipartFile file) {
    this.imageId = imageId;
    this.file = file;
  }

  public UUID getImageId() {
    return imageId;
  }

  public MultipartFile getFile() {
    return file;
  }
}
