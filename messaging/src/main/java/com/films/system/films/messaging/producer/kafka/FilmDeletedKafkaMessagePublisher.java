package com.films.system.films.messaging.producer.kafka;

import com.films.system.common.application.dto.message.FilmMessageDto;
import com.films.system.common.kafka.producer.KafkaProducer;
import com.films.system.films.domain.events.FilmDeletedEvent;
import com.films.system.films.domain.ports.output.message.publisher.FilmDeletedRequestMessagePublisher;
import com.films.system.films.messaging.mapper.FilmMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class FilmDeletedKafkaMessagePublisher implements FilmDeletedRequestMessagePublisher {

  private final KafkaProducer<FilmMessageDto> kafkaProducer;
  private final FilmMessagingDataMapper filmMessagingDataMapper;

  @Value("${kafka-producer.film-deleted-topic}")
  private String topic;

  @Override
  public void publish(FilmDeletedEvent domainEvent) {
    String filmId = domainEvent.getFilm().getFilmId().getValue().toString();
    try {
      kafkaProducer.send(topic, filmMessagingDataMapper.filmToFilmMessageDto(domainEvent.getFilm()));
      log.info("Film deleted sent to kafka for film id: {}", filmId);
    } catch (Exception e) {
      log.error(
          "Error while sending Film message to kafka with film id: {}, error: {}",
          filmId,
          e.getMessage(),
          e);
    }
  }
}
