package me.namila.food_ordering.payment.domain.exception;

import me.namila.food_ordering.common.exception.DomainException;

/**
 * The type Payment not found exception.
 */
public class PaymentNotFoundException extends DomainException {
  /**
   * Instantiates a new Payment not found exception.
   *
   * @param message the message
   */
  public PaymentNotFoundException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Payment not found exception.
   *
   * @param message the message
   * @param cause   the cause
   */
  public PaymentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
