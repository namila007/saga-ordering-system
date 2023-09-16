package me.namila.food_ordering.order.domain.application.dto.create;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.namila.food_ordering.common.valueobject.OrderStatus;

/**
 * The type Create order response.
 */
@Getter
@AllArgsConstructor
@Builder
public class CreateOrderResponse {
  @NotNull
  private final UUID orderTrackingId;
  @NotNull
  private final OrderStatus orderStatus;
  @NotNull
  private final String message;
}
