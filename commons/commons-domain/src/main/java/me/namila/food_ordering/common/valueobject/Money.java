package me.namila.food_ordering.common.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.UnaryOperator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

/**
 * The type Money.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Money {

  public static final Money ZERO_MONEY = new Money(BigDecimal.ZERO);

  @Getter
  @EqualsAndHashCode.Include
  private final BigDecimal amount;
  /**
   * The Scale money to two digits.
   */
  UnaryOperator<BigDecimal> scaleMoneyToTwoDigits =
      bigDecimalMoney -> bigDecimalMoney.setScale(2, RoundingMode.HALF_EVEN);

  /**
   * Instantiates a new Money.
   *
   * @param amount the amount
   */
  public Money(BigDecimal amount) {
    this.amount = amount;
  }

  /**
   * Is amount greater than zero boolean.
   *
   * @return the boolean
   */
  public boolean isAmountGreaterThanZero() {
    return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
  }

  /**
   * Is amount greater boolean.
   *
   * @param money the money
   * @return the boolean
   */
  public boolean isAmountGreater(@NonNull Money money) {
    return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
  }

  /**
   * Add money.
   *
   * @param money the money
   * @return the money
   */
  public Money add(@NonNull Money money) {
    return new Money(scaleMoneyToTwoDigits.apply(this.amount.add(money.getAmount())));
  }

  /**
   * Subtract money.
   *
   * @param money the money
   * @return the money
   */
  public Money subtract(@NonNull Money money) {
    return new Money(scaleMoneyToTwoDigits.apply(this.amount.subtract(money.getAmount())));
  }

  /**
   * Multiply money.
   *
   * @param money the money
   * @return the money
   */
  public Money multiply(@NonNull Money money) {
    return new Money(scaleMoneyToTwoDigits.apply(this.amount.multiply(money.getAmount())));
  }

  /**
   * Multiply money.
   *
   * @param scale the scale
   * @return the money
   */
  public Money multiply(int scale) {
    return new Money(scaleMoneyToTwoDigits.apply(this.amount.multiply(new BigDecimal(scale))));
  }

  /**
   * Divide money.
   *
   * @param money the money
   * @return the money
   */
  public Money divide(@NonNull Money money) {
    return new Money(this.amount.divide(money.getAmount(), 2, RoundingMode.HALF_EVEN));
  }

  /**
   * Divide money.
   *
   * @param scale the scale
   * @return the money
   */
  public Money divide(int scale) {
    return new Money(this.amount.divide(new BigDecimal(scale), 2, RoundingMode.HALF_EVEN));
  }
}
