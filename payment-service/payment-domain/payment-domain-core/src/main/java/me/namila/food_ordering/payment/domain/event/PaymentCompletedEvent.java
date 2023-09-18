package me.namila.food_ordering.payment.domain.event;

import java.time.ZonedDateTime;
import java.util.Collections;

import me.namila.food_ordering.payment.domain.entity.Payment;

/**
 * The type Payment completed event.
 */
public class PaymentCompletedEvent extends PaymentEvent {

  /**
   * Instantiates a new Payment completed event.
   *
   * @param payment   the payment
   * @param createdAt the created at
   */
  public PaymentCompletedEvent(Payment payment, ZonedDateTime createdAt) {
    super(payment, createdAt, Collections.emptyList());
  }
}
