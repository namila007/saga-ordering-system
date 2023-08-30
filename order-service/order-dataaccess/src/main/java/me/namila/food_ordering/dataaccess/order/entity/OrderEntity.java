package me.namila.food_ordering.dataaccess.order.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.*;
import me.namila.food_ordering.common.valueobject.OrderStatus;

/**
 * The type Order entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order")
public class OrderEntity {
  @Id
  @EqualsAndHashCode.Include
  private UUID id;
  @Column(name = "customer_id")
  private UUID customerId;
  @Column(name = "restaurant_id")
  private UUID restaurantId;
  @Column(name = "tracking_id")
  private UUID trackingId;
  private BigDecimal price;
  @Column(name = "order_status")
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
  @Column(name = "failure_messages")
  private String failureMessages;

  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
  private OrderAddressEntity address;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItemEntity> items;


  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

}
