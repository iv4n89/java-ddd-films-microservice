package com.films.system.films.application.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmAddImageDto {
  private UUID imageId;
}
