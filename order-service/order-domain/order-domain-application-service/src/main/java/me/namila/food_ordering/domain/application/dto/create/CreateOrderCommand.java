package me.namila.food_ordering.domain.application.dto.create;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * The type Create order command.
 */
@Getter
@AllArgsConstructor
@Builder
public class CreateOrderCommand {
  @NotNull
  private final UUID customerId;
  @NotNull
  private final UUID restaurantId;
  @NotNull
  private final BigDecimal price;
  @NotNull
  private final List<OrderItem> items;
  @NotNull
  private final OrderAddress address;
}