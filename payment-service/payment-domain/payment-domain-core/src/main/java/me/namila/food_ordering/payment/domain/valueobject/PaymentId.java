package me.namila.food_ordering.payment.domain.valueobject;

import java.util.UUID;

import me.namila.food_ordering.common.valueobject.BaseId;

/**
 * The type Payment id.
 */
public class PaymentId extends BaseId<UUID> {
  /**
   * Instantiates a new Payment id.
   *
   * @param value the value
   */
  public PaymentId(UUID value) {
    super(value);
  }
}
