package me.namila.food_ordering.order.dataaccess.restaurant.entity.key;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Restaurant entity id.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
@EqualsAndHashCode
public class RestaurantEntityId implements Serializable {


  @Column(name = "restaurant_id")
  private UUID restaurantId;
  @Column(name = "product_id")
  private UUID productId;
}
