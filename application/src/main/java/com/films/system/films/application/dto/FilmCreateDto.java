package com.films.system.films.application.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmCreateDto {
  private UUID id;
  private String title;
  private Date launchDate;
}
