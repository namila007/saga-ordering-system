package me.namila.food_ordering.domain.application.ports.output.repository;

import java.util.Optional;

import me.namila.food_ordering.domain.core.entity.Order;
import me.namila.food_ordering.domain.core.valueobject.TrackingId;

/**
 * The interface Order repository.
 */

public interface OrderRepository {
  /**
   * Save order.
   *
   * @param order the order
   * @return the order
   */
  Order save(Order order);

  /**
   * Find by tracking id optional.
   *
   * @param trackingId the tracking id
   * @return the optional
   */
  Optional<Order> findByTrackingId(TrackingId trackingId);
}
