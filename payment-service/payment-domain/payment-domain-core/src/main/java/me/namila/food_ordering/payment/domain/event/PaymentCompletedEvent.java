package me.namila.food_ordering.payment.domain.event;

import java.time.ZonedDateTime;
import java.util.Collections;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.payment.domain.entity.Payment;

/**
 * The type Payment completed event.
 */
public class PaymentCompletedEvent extends PaymentEvent {

  private final DomainEventPublisher<PaymentCompletedEvent> publisher;

  /**
   * Instantiates a new Payment completed event.
   *
   * @param payment   the payment
   * @param createdAt the created at
   * @param publisher the publisher
   */
  public PaymentCompletedEvent(Payment payment, ZonedDateTime createdAt,
                               DomainEventPublisher<PaymentCompletedEvent> publisher) {
    super(payment, createdAt, Collections.emptyList());
    this.publisher = publisher;
  }

  @Override
  public void fire() {
    publisher.publish(this);
  }
}
