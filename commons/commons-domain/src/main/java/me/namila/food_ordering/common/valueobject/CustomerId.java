package me.namila.food_ordering.common.valueobject;

import java.util.UUID;

import lombok.EqualsAndHashCode;

/**
 * The type Customer id.
 */
@EqualsAndHashCode(callSuper = true)
public class CustomerId extends BaseId<UUID> {

  /**
   * Instantiates a new Customer id.
   *
   * @param id the id
   */
  public CustomerId(UUID id) {
    super(id);
  }
}
