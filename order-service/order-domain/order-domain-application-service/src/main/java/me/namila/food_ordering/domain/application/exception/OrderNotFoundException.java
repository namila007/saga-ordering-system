package me.namila.food_ordering.domain.application.exception;

import me.namila.food_ordering.common.exception.DomainException;

/**
 * The type Order not found exception.
 */
public class OrderNotFoundException extends DomainException {
  /**
   * Instantiates a new Order not found exception.
   *
   * @param message the message
   */
  public OrderNotFoundException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Order not found exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public OrderNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
