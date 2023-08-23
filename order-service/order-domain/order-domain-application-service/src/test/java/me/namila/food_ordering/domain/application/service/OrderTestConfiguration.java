package me.namila.food_ordering.domain.application.service;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import me.namila.food_ordering.domain.application.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import me.namila.food_ordering.domain.application.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import me.namila.food_ordering.domain.application.ports.output.message.publisher.payment.OrderPaidPaymentRequestMessagePublisher;
import me.namila.food_ordering.domain.application.ports.output.repository.CustomerRepository;
import me.namila.food_ordering.domain.application.ports.output.repository.OrderRepository;
import me.namila.food_ordering.domain.application.ports.output.repository.RestaurantRepository;
import me.namila.food_ordering.domain.core.service.OrderDomainService;
import me.namila.food_ordering.domain.core.service.OrderDomainServiceImpl;

@SpringBootApplication(scanBasePackages = "me.namila.food_ordering")
class OrderTestConfiguration {
  @Bean
  public OrderCreatedPaymentRequestMessagePublisher OrderCreatedPaymentRequestMessagePublisher() {
    return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
  }

  @Bean
  public OrderPaidPaymentRequestMessagePublisher OrderPaidPaymentRequestMessagePublisher() {
    return Mockito.mock(OrderPaidPaymentRequestMessagePublisher.class);
  }

  @Bean
  public OrderCancelledPaymentRequestMessagePublisher OrderCancelledPaymentRequestMessagePublisher() {
    return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
  }

  @Bean
  public OrderRepository orderRepository() {
    return Mockito.mock(OrderRepository.class);
  }

  @Bean
  public CustomerRepository customerRepository() {
    return Mockito.mock(CustomerRepository.class);
  }

  @Bean
  public RestaurantRepository restaurantRepository() {
    return Mockito.mock(RestaurantRepository.class);
  }


  @Bean
  public OrderDomainService orderDomainService() {
    return new OrderDomainServiceImpl();
  }
}
