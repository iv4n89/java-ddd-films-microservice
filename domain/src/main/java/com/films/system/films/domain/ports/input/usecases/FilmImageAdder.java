package com.films.system.films.domain.ports.input.usecases;

import com.films.system.films.domain.events.FilmImageAddedEvent;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FilmImageAdder {
    void addImage(UUID filmId, UUID imageId);
    FilmImageAddedEvent uploadImage(UUID filmId, MultipartFile file);
}
