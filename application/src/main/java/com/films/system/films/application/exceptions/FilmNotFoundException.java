package com.films.system.films.application.exceptions;

import com.films.system.common.application.exceptions.ApplicationException;

public class FilmNotFoundException extends ApplicationException {
    public FilmNotFoundException(String message) {
        super(message);
    }
}
