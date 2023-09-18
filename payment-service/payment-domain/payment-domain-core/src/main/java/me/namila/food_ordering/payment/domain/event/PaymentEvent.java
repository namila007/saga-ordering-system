package me.namila.food_ordering.payment.domain.event;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.namila.food_ordering.common.event.DomainEvent;
import me.namila.food_ordering.payment.domain.entity.Payment;

/**
 * The type Payment event.
 */
@Getter
@AllArgsConstructor
public abstract class PaymentEvent implements DomainEvent<Payment> {
  private final Payment payment;
  private final ZonedDateTime createdAt;
  private final List<String> failureMessages;
}
