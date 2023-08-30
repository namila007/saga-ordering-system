package me.namila.food_ordering.dataaccess.order.entity.key;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import me.namila.food_ordering.dataaccess.order.entity.OrderEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
@EqualsAndHashCode
public class OrderItemEntityKey implements Serializable {

  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "order_id")
  private OrderEntity order;
}