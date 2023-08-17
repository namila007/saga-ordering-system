package me.namila.food_ordering.domain.application.ports.output.repository;

import java.util.Optional;
import java.util.UUID;

import me.namila.food_ordering.domain.core.entity.Customer;

/**
 * The interface Customer repository.
 */

public interface CustomerRepository {

  /**
   * Find customer optional.
   *
   * @param customerId the customer id
   * @return the optional
   */
  Optional<Customer> findCustomer(UUID customerId);

}
