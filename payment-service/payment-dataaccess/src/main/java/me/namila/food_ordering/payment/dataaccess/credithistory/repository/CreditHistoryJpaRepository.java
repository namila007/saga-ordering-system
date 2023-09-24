package me.namila.food_ordering.payment.dataaccess.credithistory.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.namila.food_ordering.payment.dataaccess.credithistory.entity.CreditHistoryEntity;

/**
 * The interface Credit history jpa repository.
 */
@Repository
public interface CreditHistoryJpaRepository extends JpaRepository<CreditHistoryEntity, UUID> {

  /**
   * Find by customer id optional.
   *
   * @param customerId the customer id
   * @return the optional
   */
  Optional<List<CreditHistoryEntity>> findByCustomerId(UUID customerId);


}
