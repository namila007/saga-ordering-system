package me.namila.food_ordering.common.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Base entity.
 *
 * @param <ID> the type parameter
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseEntity<ID> {

  @Getter
  @Setter
  @EqualsAndHashCode.Include
  private ID id;

}
