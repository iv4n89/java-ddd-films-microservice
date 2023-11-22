package com.films.system.films.application.exceptions;

import com.films.system.common.application.exceptions.ApplicationException;

public class FilmApplicationException extends ApplicationException {
    public FilmApplicationException(String message) {
        super(message);
    }
}
