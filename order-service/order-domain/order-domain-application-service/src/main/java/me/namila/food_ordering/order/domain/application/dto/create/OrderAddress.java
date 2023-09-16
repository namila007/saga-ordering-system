package me.namila.food_ordering.order.domain.application.dto.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * The type Order address.
 */
@Getter
@AllArgsConstructor
@Builder
public class OrderAddress {
  @NotNull
  @Max(50)
  private final String street;
  @NotNull
  @Max(10)
  private final String postalCode;

  @NotNull
  @Max(50)
  private final String city;
}
