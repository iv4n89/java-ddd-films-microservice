package com.films.system.films.domain.ports.input.message.listener;

import com.films.system.films.domain.models.Comment;

public interface CommentDeletedEventListener {
  void commentDeleted(Comment comment);
}
