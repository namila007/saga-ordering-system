package me.namila.food_ordering.common.exception;

/**
 * The type Domain exception.
 */
public class DomainException extends RuntimeException {
  /**
   * Instantiates a new Domain exception.
   *
   * @param message the message
   */
  public DomainException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Domain exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public DomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
