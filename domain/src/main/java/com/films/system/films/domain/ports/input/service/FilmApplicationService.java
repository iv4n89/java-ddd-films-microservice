package com.films.system.films.domain.ports.input.service;

import com.films.system.films.domain.models.Film;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FilmApplicationService {
    void create(UUID filmId, String filmTitle, Date filmLaunchDate);
    Optional<Film> findOne(UUID id);
    List<Film> findAll();
    void delete(UUID id);
    void addImage(UUID filmId, UUID imageId);

    void uploadImage(UUID filmId, MultipartFile file);
}
