package me.namila.food_ordering.order.domain.application.dto.track;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.namila.food_ordering.common.valueobject.OrderStatus;

/**
 * The type Track order response.
 */
@Getter
@Builder
@AllArgsConstructor
public class TrackOrderResponse {
  @NotNull
  private final UUID orderTrackingId;
  @NotNull
  private final OrderStatus orderStatus;
  private final List<String> failureMessages;
}
