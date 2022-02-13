package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        new Date(),
        exception.getMessage(),
        request.getDescription(false)
    );

    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UsuarioNotFoundException.class)
  public final ResponseEntity<Object> handleUsuarioNotFoundException(Exception exception, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        new Date(),
        exception.getMessage(),
        request.getDescription(false)
    );

    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        new Date(),
        "Erro de validação",
        exception.getBindingResult()
          .getFieldErrors()
          .stream()
          .map(fieldError -> fieldError.getField().concat(" ").concat(fieldError.getDefaultMessage()))
          .collect(Collectors.joining(", "))
    );

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
