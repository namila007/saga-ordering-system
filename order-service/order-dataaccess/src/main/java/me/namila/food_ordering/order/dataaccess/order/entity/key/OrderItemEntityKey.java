package me.namila.food_ordering.order.dataaccess.order.entity.key;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.namila.food_ordering.order.dataaccess.order.entity.OrderEntity;

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
