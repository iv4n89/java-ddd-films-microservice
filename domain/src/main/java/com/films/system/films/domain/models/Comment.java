package com.films.system.films.domain.models;

import com.films.system.common.domain.valueobject.CommentId;
import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.common.domain.valueobject.UserId;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Comment implements Serializable {

  private final CommentId commentId;
  private final String description;
  private final Integer rating;
  private final FilmId filmId;
  private final UserId userId;
  private final ZonedDateTime createdAt;

  private Comment(Builder builder) {
    commentId = builder.commentId;
    description = builder.description;
    rating = builder.rating;
    filmId = builder.filmId;
    userId = builder.userId;
    createdAt = builder.createdAt;
  }

  public static Builder builder() {
    return new Builder();
  }

  public CommentId getCommentId() {
    return commentId;
  }

  public String getDescription() {
    return description;
  }

  public Integer getRating() {
    return rating;
  }

  public FilmId getFilmId() {
    return filmId;
  }

  public UserId getUserId() {
    return userId;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public static final class Builder {
    private CommentId commentId;
    private String description;
    private Integer rating;
    private FilmId filmId;
    private UserId userId;
    private ZonedDateTime createdAt;

    private Builder() {}

    public Builder commentId(CommentId val) {
      commentId = val;
      return this;
    }

    public Builder description(String val) {
      description = val;
      return this;
    }

    public Builder rating(Integer val) {
      rating = val;
      return this;
    }

    public Builder filmId(FilmId val) {
      filmId = val;
      return this;
    }

    public Builder userId(UserId val) {
      userId = val;
      return this;
    }

    public Builder createdAt(ZonedDateTime val) {
      createdAt = val;
      return this;
    }

    public Comment build() {
      return new Comment(this);
    }
  }
}
