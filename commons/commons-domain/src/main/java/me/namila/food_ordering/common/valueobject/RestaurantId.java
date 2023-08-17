package me.namila.food_ordering.common.valueobject;

import java.util.UUID;

import lombok.EqualsAndHashCode;

/**
 * The type Restaurant id.
 */
@EqualsAndHashCode(callSuper = true)
public class RestaurantId extends BaseId<UUID> {

  /**
   * Instantiates a new Restaurant id.
   *
   * @param id the id
   */
  public RestaurantId(UUID id) {
    super(id);
  }
}
