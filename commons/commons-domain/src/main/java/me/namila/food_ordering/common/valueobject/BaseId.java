package me.namila.food_ordering.common.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * The type Base id.
 *
 * @param <ID> the type parameter
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseId<ID> {

  @Getter
  @EqualsAndHashCode.Include
  private final ID baseId;

  /**
   * Instantiates a new Base id.
   *
   * @param baseId the base id
   */
  protected BaseId(ID baseId) {
    this.baseId = baseId;
  }
}
