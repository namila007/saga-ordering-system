package me.namila.food_ordering.order.application.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.common.application.exception.ErrorDTO;
import me.namila.food_ordering.common.application.exception.GlobalExceptionHandler;
import me.namila.food_ordering.order.application.exception.OrderApplicationException;
import me.namila.food_ordering.order.domain.application.exception.OrderNotFoundException;
import me.namila.food_ordering.order.domain.core.exception.OrderDomainException;

/**
 * The type Order global exception handler.
 */
@Slf4j
@ControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {

  /**
   * Handle order domain exception error dto.
   *
   * @param orderDomainException the order domain exception
   * @return the error dto
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {OrderDomainException.class})
  @ResponseBody
  public ErrorDTO handleOrderDomainException(OrderDomainException orderDomainException) {
    log.error("OrderGlobalExceptionHandler::handleOrderDomainException - Error Occurred - {}",
        orderDomainException.getMessage(), orderDomainException);
    return ErrorDTO.builder().httpStatus(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(orderDomainException.getMessage()).build();
  }

  /**
   * Handle order not found exception error dto.
   *
   * @param orderNotFoundException the order not found exception
   * @return the error dto
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = {OrderNotFoundException.class})
  @ResponseBody
  public ErrorDTO handleOrderNotFoundException(OrderNotFoundException orderNotFoundException) {
    log.error("OrderGlobalExceptionHandler::handleOrderNotFoundException - Error Occurred - {}",
        orderNotFoundException.getMessage(), orderNotFoundException);
    return ErrorDTO.builder().httpStatus(HttpStatus.NOT_FOUND.getReasonPhrase())
        .message(orderNotFoundException.getMessage()).build();
  }

  /**
   * Handle order application exception error dto.
   *
   * @param orderApplicationException the order application exception
   * @return the error dto
   */
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  @ExceptionHandler(value = {OrderApplicationException.class})
  @ResponseBody
  public ErrorDTO handleOrderApplicationException(
      OrderApplicationException orderApplicationException) {
    log.error("OrderGlobalExceptionHandler::handleOrderApplicationException - Error Occurred - {}",
        orderApplicationException.getMessage(), orderApplicationException);
    return ErrorDTO.builder().httpStatus(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase())
        .message(orderApplicationException.getMessage()).build();
  }
}
