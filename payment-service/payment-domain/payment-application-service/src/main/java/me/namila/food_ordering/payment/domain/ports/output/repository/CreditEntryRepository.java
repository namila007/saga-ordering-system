package me.namila.food_ordering.payment.domain.ports.output.repository;

import java.util.Optional;

import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.payment.domain.entity.CreditEntry;

/**
 * The interface Credit entry repository.
 */
public interface CreditEntryRepository {
  /**
   * Save credit entry.
   *
   * @param creditEntry the credit entry
   * @return the credit entry
   */
  CreditEntry save(CreditEntry creditEntry);

  /**
   * Find by customer id optional.
   *
   * @param customerId the customer id
   * @return the optional
   */
  Optional<CreditEntry> findByCustomerId(CustomerId customerId);

}
