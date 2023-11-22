package com.films.system.films.domain.ports.output.message.publisher;

import com.films.system.common.domain.events.publisher.DomainPublisher;
import com.films.system.films.domain.events.FilmCreatedEvent;

public interface FilmCreatedRequestMessagePublisher extends DomainPublisher<FilmCreatedEvent> {}
