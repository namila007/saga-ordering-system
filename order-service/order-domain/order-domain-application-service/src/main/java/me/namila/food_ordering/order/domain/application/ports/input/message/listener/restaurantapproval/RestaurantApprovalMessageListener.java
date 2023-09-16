package me.namila.food_ordering.order.domain.application.ports.input.message.listener.restaurantapproval;

import me.namila.food_ordering.order.domain.application.dto.message.RestaurantApprovalResponse;

/**
 * The interface Restaurant approval message listener.
 */
public interface RestaurantApprovalMessageListener {
  /**
   * Order approved.
   *
   * @param restaurantApprovalResponse the restaurant approval response
   */
  void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

  /**
   * Order rejected.
   *
   * @param restaurantApprovalResponse the restaurant approval response
   */
  void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
