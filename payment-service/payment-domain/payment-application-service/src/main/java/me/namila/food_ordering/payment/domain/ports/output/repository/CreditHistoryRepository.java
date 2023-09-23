package me.namila.food_ordering.payment.domain.ports.output.repository;

import java.util.List;
import java.util.Optional;

import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.payment.domain.entity.CreditHistory;

/**
 * The interface Credit history repository.
 */
public interface CreditHistoryRepository {
  /**
   * Save credit history.
   *
   * @param creditHistory the credit history
   * @return the credit history
   */
  CreditHistory save(CreditHistory creditHistory);

  /**
   * Find by customer id optional.
   *
   * @param customerId the customer id
   * @return the optional
   */
  Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);

}
