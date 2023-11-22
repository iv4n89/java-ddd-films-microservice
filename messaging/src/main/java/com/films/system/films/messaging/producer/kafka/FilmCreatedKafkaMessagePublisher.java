package com.films.system.films.messaging.producer.kafka;

import com.films.system.common.application.dto.message.FilmMessageDto;
import com.films.system.common.kafka.producer.KafkaProducer;
import com.films.system.films.domain.events.FilmCreatedEvent;
import com.films.system.films.domain.ports.output.message.publisher.FilmCreatedRequestMessagePublisher;
import com.films.system.films.messaging.mapper.FilmMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class FilmCreatedKafkaMessagePublisher implements FilmCreatedRequestMessagePublisher {

  private final KafkaProducer<FilmMessageDto> kafkaProducer;
  private final FilmMessagingDataMapper filmMessagingDataMapper;

  @Value("${kafka-producer.film-created-topic}")
  private String topic;

  @Override
  public void publish(FilmCreatedEvent domainEvent) {
    String filmId = domainEvent.getFilm().getFilmId().getValue().toString();
    try {
      kafkaProducer.send(
          topic, filmMessagingDataMapper.filmToFilmMessageDto(domainEvent.getFilm()));
      log.info("Film sent to kafka for film id: {}", filmId);
    } catch (Exception e) {
      log.error(
          "Error while sending Film message to kafka with film id: {}, error: {}",
          filmId,
          e.getMessage(),
          e);
    }
  }
}
