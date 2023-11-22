package com.films.system.films.domain.ports.output.message.publisher;

import com.films.system.common.domain.events.publisher.DomainPublisher;
import com.films.system.films.domain.events.FilmDeletedEvent;

public interface FilmDeletedRequestMessagePublisher extends DomainPublisher<FilmDeletedEvent> {}
