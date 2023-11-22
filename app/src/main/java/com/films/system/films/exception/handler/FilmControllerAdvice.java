package com.films.system.films.exception.handler;

import com.films.system.common.application.handler.ErrorDto;
import com.films.system.common.application.handler.GlobalExceptionHandler;
import com.films.system.films.application.exceptions.FilmNotFoundException;
import com.films.system.films.domain.exception.FilmDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class FilmControllerAdvice extends GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(value = {FilmDomainException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto handleException(FilmDomainException filmDomainException) {
    log.error(filmDomainException.getMessage(), filmDomainException);
    return ErrorDto.builder()
        .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(filmDomainException.getMessage())
        .build();
  }

  @ResponseBody
  @ExceptionHandler(value = {FilmNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorDto handleException(FilmNotFoundException filmNotFoundException) {
    log.error(filmNotFoundException.getMessage(), filmNotFoundException);
    return ErrorDto.builder()
        .code(HttpStatus.NOT_FOUND.getReasonPhrase())
        .message(filmNotFoundException.getMessage())
        .build();
  }
}
