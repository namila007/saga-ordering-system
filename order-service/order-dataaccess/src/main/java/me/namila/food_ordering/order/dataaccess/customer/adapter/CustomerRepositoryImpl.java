package me.namila.food_ordering.order.dataaccess.customer.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import me.namila.food_ordering.order.dataaccess.customer.mapper.CustomerDataAccessMapper;
import me.namila.food_ordering.order.dataaccess.customer.repository.CustomerJpaRepository;
import me.namila.food_ordering.order.domain.application.ports.output.repository.CustomerRepository;
import me.namila.food_ordering.order.domain.core.entity.Customer;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {
  private final CustomerJpaRepository customerJpaRepository;
  private final CustomerDataAccessMapper customerDataAccessMapper;

  public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository,
      CustomerDataAccessMapper customerDataAccessMapper) {
    this.customerJpaRepository = customerJpaRepository;
    this.customerDataAccessMapper = customerDataAccessMapper;
  }

  @Override
  public Optional<Customer> findCustomer(UUID customerId) {
    return customerJpaRepository.findById(customerId).map(customerDataAccessMapper::toCustomer);
  }
}
