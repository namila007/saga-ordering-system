package me.namila.food_ordering.payment.domain.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.common.valueobject.OrderId;
import me.namila.food_ordering.payment.domain.dto.PaymentRequest;
import me.namila.food_ordering.payment.domain.entity.Payment;

/**
 * The type Payment data mapper.
 */
@Component
public class PaymentDataMapper {

    /**
     * Payment request model to payment payment.
     *
     * @param paymentRequest the payment request
     * @return the payment
     */
    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.Builder.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.getOrderId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice())).build();
    }
}
