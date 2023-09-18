package me.namila.food_ordering.payment.domain.valueobject;

import java.util.UUID;

import me.namila.food_ordering.common.valueobject.BaseId;

/**
 * The type Credit history id.
 */
public class CreditHistoryId extends BaseId<UUID> {
  /**
   * Instantiates a new Credit history id.
   *
   * @param value the value
   */
  public CreditHistoryId(UUID value) {
    super(value);
  }
}
