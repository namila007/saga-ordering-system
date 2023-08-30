package me.namila.food_ordering.dataaccess.customer.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.namila.food_ordering.dataaccess.customer.entity.CustomerEntity;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
}
