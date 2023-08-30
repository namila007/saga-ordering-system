package me.namila.food_ordering.dataaccess.order.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

/**
 * The type Order address entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_Address")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderAddressEntity {

  @Id
  @EqualsAndHashCode.Include
  private UUID id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "order_id")
  private OrderEntity order;
  private String street;
  private String postalCode;


  private String city;
}
