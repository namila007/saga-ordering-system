package me.namila.food_ordering.payment.domain.entity;

import lombok.Getter;
import lombok.Setter;
import me.namila.food_ordering.common.entity.BaseEntity;
import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.payment.domain.valueobject.CreditEntryId;


/**
 * The type Credit entry.
 */
@Getter

public class CreditEntry extends BaseEntity<CreditEntryId> {

  private final CustomerId customerId;

  @Setter
  private Money totalCreditAmount;

  private CreditEntry(Builder builder) {
    setId(builder.id);
    customerId = builder.customerId;
    setTotalCreditAmount(builder.totalCreditAmount);
  }

  /**
   * Add credit amount.
   *
   * @param amount the amount
   */
  public void addCreditAmount(Money amount) {
    totalCreditAmount = totalCreditAmount.add(amount);
  }

  /**
   * Subtract credit amount.
   *
   * @param amount the amount
   */
  public void subtractCreditAmount(Money amount) {
    totalCreditAmount = totalCreditAmount.subtract(amount);
  }


  public static final class Builder {
    private CreditEntryId id;
    private CustomerId customerId;
    private Money totalCreditAmount;

    private Builder() {
    }

    public static Builder builder() {
      return new Builder();
    }

    public Builder creditEntryId(CreditEntryId val) {
      id = val;
      return this;
    }

    public Builder customerId(CustomerId val) {
      customerId = val;
      return this;
    }

    public Builder totalCreditAmount(Money val) {
      totalCreditAmount = val;
      return this;
    }

    public CreditEntry build() {
      return new CreditEntry(this);
    }
  }
}
