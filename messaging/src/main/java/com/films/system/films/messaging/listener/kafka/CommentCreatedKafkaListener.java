package com.films.system.films.messaging.listener.kafka;

import com.films.system.common.application.dto.message.CommentCreatedMessageDto;
import com.films.system.common.domain.valueobject.FilmId;
import com.films.system.common.kafka.consumer.KafkaConsumer;
import com.films.system.films.domain.ports.input.message.listener.CommentCreatedEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommentCreatedKafkaListener implements KafkaConsumer<CommentCreatedMessageDto> {

  private final CommentCreatedEventListener commentCreatedEventListener;

  @Override
  @KafkaListener(
      id = "${kafka-consumer-config.comment-created-group-id}",
      topics = "${film-service.comment-created-topic}")
  public void receive(CommentCreatedMessageDto payload) {
    log.info("Received message with payload: {}", payload);

    commentCreatedEventListener.commentCreated(
        new FilmId(payload.getFilmId()), payload.getMeanRating());
  }
}
