package me.namila.food_ordering.order.dataaccess.restaurant.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.namila.food_ordering.order.dataaccess.restaurant.entity.key.RestaurantEntityId;

/**
 * The type Restaurant entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "order_restaurant_m_view", schema = "restaurant")
public class RestaurantEntity {

  @EqualsAndHashCode.Include
  @EmbeddedId
  private RestaurantEntityId restaurantEntityId;
  @Column(name = "restaurant_name")
  private String restaurantName;
  @Column(name = "restaurant_active")
  private Boolean restaurantActive;
  @Column(name = "product_name")
  private String productName;
  @Column(name = "product_price")
  private BigDecimal productPrice;

}
