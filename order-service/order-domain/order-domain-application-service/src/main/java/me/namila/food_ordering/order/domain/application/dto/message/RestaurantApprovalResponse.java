package me.namila.food_ordering.order.domain.application.dto.message;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.namila.food_ordering.common.valueobject.OrderApprovalStatus;

/**
 * The type Restaurant approval response.
 */
@Getter
@AllArgsConstructor
@Builder
public class RestaurantApprovalResponse {
  private String id;
  private String sagaId;
  private String orderId;
  private String restaurantId;
  private OrderApprovalStatus orderApprovalStatus;
  private List<String> failureMessages;
}
