package me.namila.food_ordering.dataaccess.order.entity;

import java.math.BigDecimal;
import java.util.UUID;

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
import me.namila.food_ordering.dataaccess.order.entity.key.OrderItemEntityKey;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_items")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItemEntity {

  @EmbeddedId
  @EqualsAndHashCode.Include
  private OrderItemEntityKey orderItem;

  @Column(name = "product_id")
  private UUID productId;
  private BigDecimal price;
  private Integer quantity;
  @Column(name = "sub_total")
  private BigDecimal subTotal;
}
