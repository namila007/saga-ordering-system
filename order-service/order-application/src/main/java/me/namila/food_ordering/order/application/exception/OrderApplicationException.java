package me.namila.food_ordering.order.application.exception;

/**
 * The type Order application exception.
 */
public class OrderApplicationException extends RuntimeException {
  /**
   * Instantiates a new Order application exception.
   *
   * @param message the message
   */
  public OrderApplicationException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Order application exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public OrderApplicationException(String message, Throwable cause) {
    super(message, cause);
  }
}
