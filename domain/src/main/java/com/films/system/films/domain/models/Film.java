package com.films.system.films.domain.models;

import com.films.system.common.domain.AggregateRoot;
import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.common.domain.valueobject.ImageId;
import com.films.system.films.domain.valueobjects.FilmLaunchDate;
import com.films.system.films.domain.valueobjects.FilmMeanRating;
import com.films.system.films.domain.valueobjects.FilmTitle;

import java.io.Serializable;
import java.util.Objects;

public class Film extends AggregateRoot implements Serializable {

  private final FilmId filmId;
  private final FilmTitle filmTitle;
  private final FilmLaunchDate launchDate;
  private FilmMeanRating meanRating;
  private ImageId imageId;

  private Film(Builder builder) {
    filmId = builder.id;
    filmTitle = builder.title;
    launchDate = builder.launchDate;
    imageId = builder.imageId;
    this.meanRating = builder.meanRating;
  }

  public static Builder builder() {
    return new Builder();
  }

  public FilmId getFilmId() {
    return filmId;
  }

  public FilmTitle getFilmTitle() {
    return filmTitle;
  }

  public FilmLaunchDate getLaunchDate() {
    return launchDate;
  }

  public FilmMeanRating getMeanRating() {
    return meanRating;
  }

  public void setMeanRating(FilmMeanRating meanRating) {
    this.meanRating = meanRating;
  }

  public ImageId getImageId() {
    return imageId;
  }

  public void setImageId(ImageId imageId) {
    this.imageId = imageId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Film film = (Film) o;
    return Objects.equals(filmId, film.filmId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filmId);
  }

  public static final class Builder {
    private FilmId id;
    private FilmTitle title;
    private FilmLaunchDate launchDate;
    private FilmMeanRating meanRating;
    private ImageId imageId;

    private Builder() {}

    public Builder id(FilmId val) {
      id = val;
      return this;
    }

    public Builder title(FilmTitle val) {
      title = val;
      return this;
    }

    public Builder launchDate(FilmLaunchDate val) {
      launchDate = val;
      return this;
    }

    public Builder meanRating(FilmMeanRating val) {
      meanRating = val;
      return this;
    }

    public Builder imageId(ImageId val) {
      imageId = val;
      return this;
    }

    public Film build() {
      return new Film(this);
    }
  }
}
