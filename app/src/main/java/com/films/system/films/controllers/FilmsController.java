package com.films.system.films.controllers;

import com.films.system.films.application.dto.FilmAddImageDto;
import com.films.system.films.application.dto.FilmCreateDto;
import com.films.system.films.application.dto.FilmResponseDto;
import com.films.system.films.domain.ports.input.service.FilmApplicationService;
import com.films.system.films.infrastructure.mapper.FilmsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmsController {

  private final FilmApplicationService filmApplicationService;
  private final FilmsMapper filmsMapper;

  @GetMapping
  public ResponseEntity<List<FilmResponseDto>> findAll() {
    List<FilmResponseDto> films =
        filmApplicationService.findAll().stream().map(filmsMapper::filmToResponseDto).toList();
    return ResponseEntity.ok(films);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FilmResponseDto> findOne(@PathVariable UUID id) {
    FilmResponseDto film =
        filmApplicationService.findOne(id).map(filmsMapper::filmToResponseDto).orElseThrow();
    return ResponseEntity.ok(film);
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody FilmCreateDto filmCreateDto) {
    filmApplicationService.create(
        filmCreateDto.getId(), filmCreateDto.getTitle(), filmCreateDto.getLaunchDate());
    return ResponseEntity.created(URI.create("/films")).build();
  }

  @PostMapping("/image/{id}")
  public ResponseEntity<Void> uploadImage(
      @PathVariable UUID id, @RequestParam("image") MultipartFile file) {
    filmApplicationService.uploadImage(id, file);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> addImage(
      @PathVariable UUID id, @RequestBody FilmAddImageDto filmAddImageDto) {
    filmApplicationService.addImage(id, filmAddImageDto.getImageId());
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    filmApplicationService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
