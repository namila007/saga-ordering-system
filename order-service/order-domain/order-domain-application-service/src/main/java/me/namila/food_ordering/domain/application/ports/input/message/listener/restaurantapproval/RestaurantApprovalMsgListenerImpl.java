package me.namila.food_ordering.domain.application.ports.input.message.listener.restaurantapproval;

import org.springframework.stereotype.Component;

import me.namila.food_ordering.domain.application.dto.message.RestaurantApprovalResponse;

@Component
public class RestaurantApprovalMsgListenerImpl implements RestaurantApprovalMessageListener {
  @Override
  public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {

  }

  @Override
  public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {

  }
}
