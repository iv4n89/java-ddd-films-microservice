package com.films.system.films.application.dto;

import java.util.Date;
import java.util.UUID;

public class FilmResponseDto {
  private final UUID id;
  private final String title;
  private final Date launchDate;
  private final Integer meanRating;
  private final UUID imageId;

  private FilmResponseDto(Builder builder) {
    id = builder.id;
    title = builder.title;
    launchDate = builder.launchDate;
    imageId = builder.imageId;
    this.meanRating = builder.meanRating;
  }

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Date getLaunchDate() {
    return launchDate;
  }

  public Integer getMeanRating() {
    return meanRating;
  }

  public UUID getImageId() {
    return imageId;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private UUID id;
    private String title;
    private Date launchDate;
    private Integer meanRating;
    private UUID imageId;

    private Builder() {}

    public Builder id(UUID val) {
      id = val;
      return this;
    }

    public Builder title(String val) {
      title = val;
      return this;
    }

    public Builder launchDate(Date val) {
      launchDate = val;
      return this;
    }

    public Builder meanRating(Integer val) {
      meanRating = val;
      return this;
    }

    public Builder imageId(UUID val) {
      imageId = val;
      return this;
    }

    public FilmResponseDto build() {
      return new FilmResponseDto(this);
    }
  }
}
