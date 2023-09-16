package me.namila.food_ordering.order.domain.application.ports.input.message.listener.payment;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.order.domain.application.dto.message.PaymentResponse;

@Slf4j
@Service
@Validated
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {
  @Override
  public void paymentCompleted(PaymentResponse paymentResponse) {

  }

  @Override
  public void paymentCancelled(PaymentResponse paymentResponse) {

  }
}
