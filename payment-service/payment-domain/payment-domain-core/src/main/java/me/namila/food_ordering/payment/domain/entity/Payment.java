package me.namila.food_ordering.payment.domain.entity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import me.namila.food_ordering.common.entity.AggregateRoot;
import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.common.valueobject.OrderId;
import me.namila.food_ordering.common.valueobject.PaymentStatus;
import me.namila.food_ordering.payment.domain.valueobject.PaymentId;

/**
 * The type Payment.
 */
@Getter
public class Payment extends AggregateRoot<PaymentId> {
  private final OrderId orderId;
  private final CustomerId customerId;
  private final Money price;

  @Setter
  private PaymentStatus paymentStatus;
  @Setter
  private ZonedDateTime createdAt;

  private Payment(Builder builder) {
    setId(builder.paymentId);
    orderId = builder.orderId;
    customerId = builder.customerId;
    price = builder.price;
    setPaymentStatus(builder.paymentStatus);
    setCreatedAt(builder.createdAt);
  }

  /**
   * Initialize payment.
   */
  public void initializePayment() {
    setId(new PaymentId(UUID.randomUUID()));
    createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
  }

  /**
   * Validate payment.
   *
   * @param failureMessages the failure messages
   */
  public void validatePayment(List<String> failureMessages) {
    if (price == null || !price.isAmountGreaterThanZero()) {
      failureMessages.add("Total price must be greater than zero!");
    }
  }


  /**
   * The type Builder.
   */
  public static final class Builder {
    private PaymentId paymentId;
    private OrderId orderId;
    private CustomerId customerId;
    private Money price;
    private PaymentStatus paymentStatus;
    private ZonedDateTime createdAt;

    private Builder() {
    }

    /**
     * Builder builder.
     *
     * @return the builder
     */
    public static Builder builder() {
      return new Builder();
    }

    /**
     * Payment id builder.
     *
     * @param val the val
     * @return the builder
     */
    public Builder paymentId(PaymentId val) {
      paymentId = val;
      return this;
    }

    /**
     * Order id builder.
     *
     * @param val the val
     * @return the builder
     */
    public Builder orderId(OrderId val) {
      orderId = val;
      return this;
    }

    /**
     * Customer id builder.
     *
     * @param val the val
     * @return the builder
     */
    public Builder customerId(CustomerId val) {
      customerId = val;
      return this;
    }

    /**
     * Price builder.
     *
     * @param val the val
     * @return the builder
     */
    public Builder price(Money val) {
      price = val;
      return this;
    }

    /**
     * Payment status builder.
     *
     * @param val the val
     * @return the builder
     */
    public Builder paymentStatus(PaymentStatus val) {
      paymentStatus = val;
      return this;
    }

    /**
     * Created at builder.
     *
     * @param val the val
     * @return the builder
     */
    public Builder createdAt(ZonedDateTime val) {
      createdAt = val;
      return this;
    }

    /**
     * Build payment.
     *
     * @return the payment
     */
    public Payment build() {
      return new Payment(this);
    }
  }
}
