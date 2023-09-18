package me.namila.food_ordering.payment.domain.entity;

import lombok.Getter;
import me.namila.food_ordering.common.entity.BaseEntity;
import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.payment.domain.valueobject.CreditHistoryId;
import me.namila.food_ordering.payment.domain.valueobject.TransactionType;

/**
 * The type Credit history.
 */
@Getter
public class CreditHistory extends BaseEntity<CreditHistoryId> {
  private final CustomerId customerId;
  private final Money amount;
  private final TransactionType transactionType;

  private CreditHistory(Builder builder) {
    setId(builder.id);
    customerId = builder.customerId;
    amount = builder.amount;
    transactionType = builder.transactionType;
  }

  public static final class Builder {
    private CreditHistoryId id;
    private CustomerId customerId;
    private Money amount;
    private TransactionType transactionType;

    private Builder() {
    }

    public static Builder builder() {
      return new Builder();
    }

    public Builder creditHistoryId(CreditHistoryId val) {
      id = val;
      return this;
    }

    public Builder customerId(CustomerId val) {
      customerId = val;
      return this;
    }

    public Builder amount(Money val) {
      amount = val;
      return this;
    }

    public Builder transactionType(TransactionType val) {
      transactionType = val;
      return this;
    }

    public CreditHistory build() {
      return new CreditHistory(this);
    }
  }
}
