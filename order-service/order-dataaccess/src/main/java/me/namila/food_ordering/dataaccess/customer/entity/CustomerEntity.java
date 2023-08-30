package me.namila.food_ordering.dataaccess.customer.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/**
 * The type Customer entity.
 */
@Entity
@Table(name = "order_customer_m_view", schema = "customers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerEntity {

  @Id
  @EqualsAndHashCode.Include
  private UUID id;

  // private String username;
  // private String firstName;
  // private String lastName;
}
