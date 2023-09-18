package me.namila.food_ordering.payment.domain.event;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import me.namila.food_ordering.payment.domain.entity.Payment;

/**
 * The type Payment failed event.
 */
public class PaymentFailedEvent extends PaymentEvent {

  /**
   * Instantiates a new Payment failed event.
   *
   * @param payment   the payment
   * @param createdAt the created at
   */
  public PaymentFailedEvent(Payment payment, ZonedDateTime createdAt) {
    super(payment, createdAt, Collections.emptyList());
  }

  /**
   * Instantiates a new Payment failed event.
   *
   * @param payment         the payment
   * @param createdAt       the created at
   * @param failureMessages the failure messages
   */
  public PaymentFailedEvent(Payment payment, ZonedDateTime createdAt,
                            List<String> failureMessages) {
    super(payment, createdAt, failureMessages);
  }
}
