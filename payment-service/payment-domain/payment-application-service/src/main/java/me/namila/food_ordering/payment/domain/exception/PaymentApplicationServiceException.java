package me.namila.food_ordering.payment.domain.exception;

import me.namila.food_ordering.common.exception.DomainException;

/**
 * The type Payment application service exception.
 */
public class PaymentApplicationServiceException extends DomainException {
  /**
   * Instantiates a new Payment application service exception.
   *
   * @param message the message
   */
  public PaymentApplicationServiceException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Payment application service exception.
   *
   * @param message the message
   * @param cause   the cause
   */
  public PaymentApplicationServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
