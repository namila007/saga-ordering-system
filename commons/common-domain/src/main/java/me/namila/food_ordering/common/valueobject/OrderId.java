package me.namila.food_ordering.common.valueobject;

import java.util.UUID;

import lombok.EqualsAndHashCode;

/**
 * The type Order id.
 */
@EqualsAndHashCode(callSuper = true)
public class OrderId extends BaseId<UUID> {

  /**
   * Instantiates a new Order id.
   *
   * @param id the id
   */
  public OrderId(UUID id) {
    super(id);
  }
}
