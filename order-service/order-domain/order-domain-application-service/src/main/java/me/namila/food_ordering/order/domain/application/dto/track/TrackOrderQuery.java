package me.namila.food_ordering.order.domain.application.dto.track;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * The type Track order query.
 */
@Getter
@Builder
@AllArgsConstructor
public class TrackOrderQuery {
  @NotNull
  private final UUID orderTracking;

}

