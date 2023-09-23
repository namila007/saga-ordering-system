package me.namila.food_ordering.payment.domain.event;

import java.time.ZonedDateTime;
import java.util.Collections;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.payment.domain.entity.Payment;

/**
 * The type Payment cancelled event.
 */
public class PaymentCancelledEvent extends PaymentEvent {
  private final DomainEventPublisher<PaymentCancelledEvent> publisher;

  /**
   * Instantiates a new Payment cancelled event.
   *
   * @param payment   the payment
   * @param createdAt the created at
   * @param publisher the publisher
   */
  public PaymentCancelledEvent(Payment payment, ZonedDateTime createdAt,
                               DomainEventPublisher<PaymentCancelledEvent> publisher) {
    super(payment, createdAt, Collections.emptyList());
    this.publisher = publisher;
  }

  @Override
  public void fire() {
    publisher.publish(this);
  }
}
