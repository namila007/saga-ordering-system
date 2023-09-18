package me.namila.food_ordering.payment.domain.exception;

import me.namila.food_ordering.common.exception.DomainException;

/**
 * The type Payment domain exception.
 */
public class PaymentDomainException extends DomainException {
  /**
   * Instantiates a new Payment domain exception.
   *
   * @param message the message
   */
  public PaymentDomainException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Payment domain exception.
   *
   * @param message the message
   * @param cause   the cause
   */
  public PaymentDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
