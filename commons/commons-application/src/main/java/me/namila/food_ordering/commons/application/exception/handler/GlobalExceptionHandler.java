package me.namila.food_ordering.commons.application.exception.handler;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Global exception handler.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handle exception error dto.
   *
   * @param e the e
   * @return the error dto
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = {Exception.class})
  @ResponseBody
  public ErrorDTO handleException(Exception e) {
    log.error("GlobalExceptionHandler::handleException - Error Occurred - {}", e.getMessage(), e);
    return ErrorDTO.builder().httpStatus(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message("Unexpected Error Occurred").build();
  }

  /**
   * Handle validation exception error dto.
   *
   * @param e the e
   * @return the error dto
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {ValidationException.class})
  @ResponseBody
  public ErrorDTO handleValidationException(ValidationException e) {
    log.error("GlobalExceptionHandler::handleValidationException - Error Occurred - {}",
        e.getMessage());
    if (e instanceof ConstraintViolationException) {
      String errorMsg = extractValidationErrors((ConstraintViolationException) e);
      log.error("GlobalExceptionHandler::handleValidationException - Constrain Violations - {}",
          errorMsg);
      return ErrorDTO.builder().httpStatus(HttpStatus.BAD_REQUEST.getReasonPhrase())
          .message(errorMsg).build();
    }

    return ErrorDTO.builder().httpStatus(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(e.getMessage()).build();
  }

  private String extractValidationErrors(ConstraintViolationException exception) {
    return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
        .collect(Collectors.joining("--"));
  }
}
