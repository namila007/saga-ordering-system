package me.namila.food_ordering.domain.core.exception;

import me.namila.food_ordering.common.exception.DomainException;

/**
 * The type Order domain exception.
 */
public class OrderDomainException extends DomainException {
  /**
   * Instantiates a new Order domain exception.
   *
   * @param message the message
   */
  public OrderDomainException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Order domain exception.
   *
   * @param message the message
   * @param cause   the cause
   */
  public OrderDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
