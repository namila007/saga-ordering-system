package me.namila.food_ordering.payment.dataaccess.payment.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.namila.food_ordering.payment.dataaccess.payment.entity.PaymentEntity;

/**
 * The interface Payment jpa repository.
 */
@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {

  /**
   * Find by order id optional.
   *
   * @param orderId the order id
   * @return the optional
   */
  Optional<PaymentEntity> findByOrderId(UUID orderId);


}
