package com.films.system.films.domain.exception;

import com.films.system.common.domain.valueobject.DomainError;

public class FilmDomainException extends DomainError {
    public FilmDomainException(String message) {
        super(message);
    }
}
