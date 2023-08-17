package me.namila.food_ordering.domain.core.valueobject;

import me.namila.food_ordering.common.valueobject.BaseId;

/**
 * The type Order item id.
 */
public class OrderItemId extends BaseId<Long> {
  /**
   * Instantiates a new Order item id.
   *
   * @param baseId the base id
   */
  public OrderItemId(Long baseId) {
    super(baseId);
  }
}
