package me.namila.food_ordering.payment.domain.event;

import java.time.ZonedDateTime;
import java.util.Collections;

import me.namila.food_ordering.payment.domain.entity.Payment;

/**
 * The type Payment cancelled event.
 */
public class PaymentCancelledEvent extends PaymentEvent {

  /**
   * Instantiates a new Payment cancelled event.
   *
   * @param payment   the payment
   * @param createdAt the created at
   */
  public PaymentCancelledEvent(Payment payment, ZonedDateTime createdAt) {
    super(payment, createdAt, Collections.emptyList());
  }
}
