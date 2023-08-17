package me.namila.food_ordering.domain.application.dto.track;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.namila.food_ordering.common.valueobject.OrderStatus;

import java.util.List;
import java.util.UUID;

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
