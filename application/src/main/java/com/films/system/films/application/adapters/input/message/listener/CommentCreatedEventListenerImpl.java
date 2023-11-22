package com.films.system.films.application.adapters.input.message.listener;

import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.films.domain.ports.input.message.listener.CommentCreatedEventListener;
import com.films.system.films.domain.ports.output.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentCreatedEventListenerImpl implements CommentCreatedEventListener {

    private final FilmRepository filmRepository;

    @Override
    public void commentCreated(FilmId filmId,Integer meanRating) {
        filmRepository.updateMeanRating(filmId, meanRating);
    }
}
