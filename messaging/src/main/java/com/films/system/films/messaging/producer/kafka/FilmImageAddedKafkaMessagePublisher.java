package com.films.system.films.messaging.producer.kafka;

import com.films.system.common.application.dto.message.FilmImageMessageDto;
import com.films.system.common.domain.valueobject.ImageId;
import com.films.system.common.kafka.producer.KafkaProducer;
import com.films.system.films.domain.events.FilmImageAddedEvent;
import com.films.system.films.domain.ports.output.message.publisher.FilmImageAddedMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class FilmImageAddedKafkaMessagePublisher implements FilmImageAddedMessagePublisher {

  private final KafkaProducer<FilmImageMessageDto> kafkaProducer;

  @Value("${kafka-producer.film-image-added-topic}")
  private String topic;

  @Override
  public void publish(FilmImageAddedEvent domainEvent) {
    MultipartFile file = domainEvent.getFile();
    UUID imageId = domainEvent.getImageId();
    try {
      kafkaProducer.send(
          topic,
          FilmImageMessageDto.builder()
              .imageId(domainEvent.getImageId().toString())
              .imageData(file.getBytes())
              .filename(file.getOriginalFilename())
              .mimeType(file.getContentType())
              .build());
      log.info("Film image sent to kafka with image id: {}", imageId);
    } catch (Exception e) {
      log.error(
          "Error while sending Image message to kafka with image id: {} and error: {}",
          imageId,
          e.getMessage(),
          e);
    }
  }
}
