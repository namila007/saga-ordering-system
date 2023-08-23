package me.namila.food_ordering.domain.core.entity;

import me.namila.food_ordering.common.entity.AggregateRoot;
import me.namila.food_ordering.common.valueobject.CustomerId;

/**
 * The type Customer.
 */
public class Customer extends AggregateRoot<CustomerId> {
  /**
   * Instantiates a new Customer.
   *
   * @param customerId the customer id
   */
  public Customer(CustomerId customerId) {
    super.setId(customerId);
  }

  /**
   * Instantiates a new Customer.
   */
  public Customer() {}
}
