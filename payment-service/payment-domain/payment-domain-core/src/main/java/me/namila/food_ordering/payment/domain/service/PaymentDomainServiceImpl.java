package me.namila.food_ordering.payment.domain.service;

import static me.namila.food_ordering.common.constant.Constant.UTC;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import me.namila.food_ordering.common.event.publisher.DomainEventPublisher;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.common.valueobject.PaymentStatus;
import me.namila.food_ordering.payment.domain.entity.CreditEntry;
import me.namila.food_ordering.payment.domain.entity.CreditHistory;
import me.namila.food_ordering.payment.domain.entity.Payment;
import me.namila.food_ordering.payment.domain.event.PaymentCancelledEvent;
import me.namila.food_ordering.payment.domain.event.PaymentCompletedEvent;
import me.namila.food_ordering.payment.domain.event.PaymentEvent;
import me.namila.food_ordering.payment.domain.event.PaymentFailedEvent;
import me.namila.food_ordering.payment.domain.valueobject.CreditHistoryId;
import me.namila.food_ordering.payment.domain.valueobject.TransactionType;

/**
 * The type Payment domain service.
 */
@Slf4j
public class PaymentDomainServiceImpl implements PaymentDomainService {

  @Override
  public PaymentEvent validateAndInitiatePayment(Payment payment, CreditEntry creditEntry,
                                                 List<CreditHistory> creditHistories, List<String> failureMessages,
                                                 DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher,
                                                 DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher) {
    payment.validatePayment(failureMessages);
    subtractCreditEntry(payment, creditEntry);
    updateCreditHistory(payment, creditHistories, TransactionType.DEBIT);
    validateCreditHistory(creditEntry, creditHistories, failureMessages);

    if (failureMessages.isEmpty()) {
      log.info("Payment is initiated for order id: {}", payment.getOrderId().getBaseId());
      payment.setPaymentStatus(PaymentStatus.COMPLETED);
      return new PaymentCompletedEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)));
    } else {
      log.info("Payment initiation is failed for order id: {}", payment.getOrderId().getBaseId());
      payment.setPaymentStatus(PaymentStatus.FAILED);
      return new PaymentFailedEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)), failureMessages);
    }
  }

  private void subtractCreditEntry(Payment payment, CreditEntry creditEntry) {
    creditEntry.subtractCreditAmount(payment.getPrice());
  }

  private void updateCreditHistory(Payment payment, List<CreditHistory> creditHistories,
                                   TransactionType transactionType) {

    creditHistories.add(CreditHistory.Builder.builder()
            .creditHistoryId(new CreditHistoryId(UUID.randomUUID())).customerId(payment.getCustomerId())
            .amount(payment.getPrice()).transactionType(transactionType).build());
  }

  private void validateCreditEntry(Payment payment, CreditEntry creditEntry,
                                   List<String> failureMessages) {
    if (payment.getPrice().isAmountGreater(creditEntry.getTotalCreditAmount())) {

      log.error("Customer with id: {} does not have enough credit for payment!",
              payment.getCustomerId().getBaseId().toString());
      failureMessages.add("Customer with id=" + payment.getCustomerId().getBaseId()
              + "does not have enough credit for payment!");
    }
  }

  private void validateCreditHistory(CreditEntry creditEntry, List<CreditHistory> creditHistories,
                                     List<String> failureMessages) {

    Money totalCreditHistory = getTotalHistoryAmount(creditHistories, TransactionType.CREDIT);
    Money totalDebitHistory = getTotalHistoryAmount(creditHistories, TransactionType.DEBIT);

    if (totalDebitHistory.isAmountGreater(totalCreditHistory)) {
      log.error("Customer with id: {} does not have enough credit according to credit history",
              creditEntry.getCustomerId().getBaseId());
      failureMessages.add("Customer with id=" + creditEntry.getCustomerId().getBaseId()
              + "does not have enough credit according to credit history!");
    }

    if (!creditEntry.getTotalCreditAmount()
            .equals(totalCreditHistory.subtract(totalCreditHistory))) {
      log.error("Credit history total is not equal to current credit for customer id: {}",
              creditEntry.getCustomerId().getBaseId());
      failureMessages.add("Credit history total is not equal to current credit for customer id: "
              + creditEntry.getCustomerId().getBaseId() + "!");
    }
  }

  private Money getTotalHistoryAmount(List<CreditHistory> creditHistories,
                                      TransactionType transactionType) {
    return creditHistories.stream()
            .filter(creditHistory -> transactionType == creditHistory.getTransactionType())
            .map(CreditHistory::getAmount).reduce(Money.ZERO_MONEY, Money::add);
  }

  @Override
  public PaymentEvent validateAndCancelPayment(Payment payment, CreditEntry creditEntry,
                                               List<CreditHistory> creditHistories, List<String> failureMessages,
                                               DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher,
                                               DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher) {
    payment.validatePayment(failureMessages);
    addCreditEntry(payment, creditEntry);
    updateCreditHistory(payment, creditHistories, TransactionType.CREDIT);

    if (failureMessages.isEmpty()) {
      log.info("Payment is cancelled for order id: {}", payment.getOrderId().getBaseId());
      payment.setPaymentStatus(PaymentStatus.CANCELLED);
      return new PaymentCancelledEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)));
    } else {
      log.info("Payment cancellation is failed for order id: {}", payment.getOrderId().getBaseId());
      payment.setPaymentStatus(PaymentStatus.FAILED);
      return new PaymentFailedEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)), failureMessages);
    }


  }

  private void addCreditEntry(Payment payment, CreditEntry creditEntry) {
    creditEntry.addCreditAmount(payment.getPrice());
  }
}
