package me.namila.food_ordering.order.dataaccess.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.namila.food_ordering.order.dataaccess.order.entity.OrderEntity;

/**
 * The interface Order repository.
 */
@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

  /**
   * Find by tracking id optional.
   *
   * @param trackingID the tracking id
   * @return the optional
   */
  Optional<OrderEntity> findByTrackingId(UUID trackingID);
}
