package com.films.system.films.messaging.mapper;

import com.films.system.common.application.dto.message.CommentMessageDto;
import com.films.system.common.domain.valueobject.CommentId;
import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.common.domain.valueobject.UserId;
import com.films.system.films.domain.models.Comment;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class CommentMessagingDataMapper {

  public Comment commentMessageDtoToComment(CommentMessageDto commentMessageDto) {
    return Comment.builder()
        .commentId(new CommentId(commentMessageDto.getId()))
        .description(commentMessageDto.getDescription())
        .rating(commentMessageDto.getRating())
        .filmId(new FilmId(commentMessageDto.getFilmId()))
        .userId(new UserId(commentMessageDto.getUserId()))
        .createdAt(ZonedDateTime.of(commentMessageDto.getCreatedAt(), ZoneId.of("UTC")))
        .build();
  }

  public CommentMessageDto commentToCommentMessageDto(Comment comment) {
    return CommentMessageDto.builder()
        .id(comment.getCommentId().getValue())
        .description(comment.getDescription())
        .rating(comment.getRating())
        .filmId(comment.getFilmId().getValue())
        .userId(comment.getUserId().getValue())
        .createdAt(comment.getCreatedAt().toLocalDateTime())
        .build();
  }
}
