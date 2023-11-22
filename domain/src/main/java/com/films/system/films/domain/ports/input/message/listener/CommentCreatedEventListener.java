package com.films.system.films.domain.ports.input.message.listener;

import com.films.system.common.domain.valueobject.FilmId;

public interface CommentCreatedEventListener {
    void commentCreated(FilmId filmId, Integer meanRating);
}
