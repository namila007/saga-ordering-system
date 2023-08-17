package me.namila.food_ordering.common.valueobject;

import java.util.UUID;

import lombok.EqualsAndHashCode;

/**
 * The type Product id.
 */
@EqualsAndHashCode(callSuper = true)
public class ProductId extends BaseId<UUID> {

  /**
   * Instantiates a new Product id.
   *
   * @param id the id
   */
  public ProductId(UUID id) {
    super(id);
  }
}
