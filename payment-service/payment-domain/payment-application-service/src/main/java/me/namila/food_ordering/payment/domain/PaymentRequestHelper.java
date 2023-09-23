package me.namila.food_ordering.payment.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.payment.domain.dto.PaymentRequest;
import me.namila.food_ordering.payment.domain.entity.CreditEntry;
import me.namila.food_ordering.payment.domain.entity.CreditHistory;
import me.namila.food_ordering.payment.domain.entity.Payment;
import me.namila.food_ordering.payment.domain.event.PaymentEvent;
import me.namila.food_ordering.payment.domain.exception.PaymentApplicationServiceException;
import me.namila.food_ordering.payment.domain.mapper.PaymentDataMapper;
import me.namila.food_ordering.payment.domain.ports.output.message.publisher.PaymentCancelledMessagePublisher;
import me.namila.food_ordering.payment.domain.ports.output.message.publisher.PaymentCompletedMessagePublisher;
import me.namila.food_ordering.payment.domain.ports.output.message.publisher.PaymentFailedMessagePublisher;
import me.namila.food_ordering.payment.domain.ports.output.repository.CreditEntryRepository;
import me.namila.food_ordering.payment.domain.ports.output.repository.CreditHistoryRepository;
import me.namila.food_ordering.payment.domain.ports.output.repository.PaymentRepository;
import me.namila.food_ordering.payment.domain.service.PaymentDomainService;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentRequestHelper {

  private final PaymentDomainService paymentDomainService;
  private final PaymentDataMapper paymentDataMapper;
  private final PaymentRepository paymentRepository;
  private final CreditEntryRepository creditEntryRepository;
  private final CreditHistoryRepository creditHistoryRepository;
  private final PaymentCompletedMessagePublisher paymentCompletedEventDomainEventPublisher;
  private final PaymentCancelledMessagePublisher paymentCancelledEventDomainEventPublisher;
  private final PaymentFailedMessagePublisher paymentFailedEventDomainEventPublisher;


  @Transactional
  public PaymentEvent persistPayment(PaymentRequest paymentRequest) {
    log.info("Received payment complete event for order id: {}", paymentRequest.getOrderId());
    Payment payment = paymentDataMapper.paymentRequestModelToPayment(paymentRequest);
    CreditEntry creditEntry = getCreditEntry(payment.getCustomerId());
    List<CreditHistory> creditHistories = getCreditHistory(payment.getCustomerId());
    List<String> failureMessages = new ArrayList<>();
    PaymentEvent paymentEvent = paymentDomainService.validateAndInitiatePayment(payment,
            creditEntry, creditHistories, failureMessages, paymentCompletedEventDomainEventPublisher,
            paymentFailedEventDomainEventPublisher);

    persistDbObjects(payment, creditEntry, creditHistories, failureMessages);

    return paymentEvent;
  }

  private List<CreditHistory> getCreditHistory(CustomerId customerId) {
    Optional<List<CreditHistory>> creditHistories =
            creditHistoryRepository.findByCustomerId(customerId);
    if (creditHistories.isEmpty()) {
      log.error("Could not find credit history for customer: {}", customerId.getBaseId());
      throw new PaymentApplicationServiceException(
              "Could not find credit history for customer: " + customerId.getBaseId());
    }
    return creditHistories.get();
  }

  private CreditEntry getCreditEntry(CustomerId customerId) {
    Optional<CreditEntry> creditEntry = creditEntryRepository.findByCustomerId(customerId);
    if (creditEntry.isEmpty()) {
      log.error("Could not find credit entry for customer: {}", customerId.getBaseId());
      throw new PaymentApplicationServiceException(
              "Could not find credit entry for customer: " + customerId.getBaseId());
    }
    return creditEntry.get();
  }


  @Transactional
  public PaymentEvent persistCancelPayment(PaymentRequest paymentRequest) {
    log.info("Received payment rollback event for order id: {}", paymentRequest.getOrderId());
    Optional<Payment> paymentResponse =
            paymentRepository.findByOrderId(UUID.fromString(paymentRequest.getOrderId()));

    if (paymentResponse.isEmpty()) {
      log.error("Payment with order id: {} could not be found!", paymentRequest.getOrderId());
      throw new PaymentApplicationServiceException(
              "Payment with order id: " + paymentRequest.getOrderId() + "could not be found!");
    }

    Payment payment = paymentResponse.get();
    CreditEntry creditEntry = getCreditEntry(payment.getCustomerId());
    List<CreditHistory> creditHistories = getCreditHistory(payment.getCustomerId());
    List<String> failureMessages = new ArrayList<>();
    PaymentEvent paymentEvent = paymentDomainService.validateAndCancelPayment(payment, creditEntry,
            creditHistories, failureMessages, paymentCancelledEventDomainEventPublisher,
            paymentFailedEventDomainEventPublisher);

    persistDbObjects(payment, creditEntry, creditHistories, failureMessages);
    return paymentEvent;
  }

  private void persistDbObjects(Payment payment, CreditEntry creditEntry,
                                List<CreditHistory> creditHistories, List<String> failureMessages) {
    paymentRepository.save(payment);
    if (failureMessages.isEmpty()) {
      creditEntryRepository.save(creditEntry);
      creditHistoryRepository.save(creditHistories.get(creditHistories.size() - 1));
    }


  }
}
