package me.namila.food_ordering.payment.domain.event;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.payment.domain.entity.Payment;

/**
 * The type Payment failed event.
 */
public class PaymentFailedEvent extends PaymentEvent {
  private final DomainEventPublisher<PaymentFailedEvent> publisher;

  /**
   * Instantiates a new Payment failed event.
   *
   * @param payment the payment
   * @param createdAt the created at
   * @param publisher
   */
  public PaymentFailedEvent(Payment payment, ZonedDateTime createdAt,
                            DomainEventPublisher<PaymentFailedEvent> publisher) {
    super(payment, createdAt, Collections.emptyList());
    this.publisher = publisher;
  }

  /**
   * Instantiates a new Payment failed event.
   *
   * @param payment the payment
   * @param createdAt the created at
   * @param failureMessages the failure messages
   * @param publisher
   */
  public PaymentFailedEvent(Payment payment, ZonedDateTime createdAt, List<String> failureMessages,
                            DomainEventPublisher<PaymentFailedEvent> publisher) {
    super(payment, createdAt, failureMessages);
    this.publisher = publisher;
  }

  @Override
  public void fire() {
    publisher.publish(this);
  }
}
