package me.namila.food_ordering.payment.domain.valueobject;

import java.util.UUID;

import me.namila.food_ordering.common.valueobject.BaseId;

/**
 * The type Credit entry id.
 */
public class CreditEntryId extends BaseId<UUID> {
  /**
   * Instantiates a new Credit entry id.
   *
   * @param value the value
   */
  public CreditEntryId(UUID value) {
    super(value);
  }
}
