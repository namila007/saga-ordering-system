package me.namila.food_ordering.payment.domain;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.payment.domain.dto.PaymentRequest;
import me.namila.food_ordering.payment.domain.event.PaymentEvent;
import me.namila.food_ordering.payment.domain.ports.input.message.listener.PaymentRequestMessageListener;

/**
 * The type Payment request message listener.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {
  private final PaymentRequestHelper paymentRequestHelper;


  @Override
  public void completePayment(PaymentRequest paymentRequest) {
    PaymentEvent paymentEvent = paymentRequestHelper.persistPayment(paymentRequest);
    fireEvent(paymentEvent);
  }

  @Override
  public void cancelPayment(PaymentRequest paymentRequest) {
    PaymentEvent paymentEvent = paymentRequestHelper.persistCancelPayment(paymentRequest);
    fireEvent(paymentEvent);
  }

  private void fireEvent(PaymentEvent paymentEvent) {
    log.info("Publishing payment event with payment id: {} and order id: {}",
            paymentEvent.getPayment().getId().getBaseId(),
            paymentEvent.getPayment().getOrderId().getBaseId());
    paymentEvent.fire();
  }
}
