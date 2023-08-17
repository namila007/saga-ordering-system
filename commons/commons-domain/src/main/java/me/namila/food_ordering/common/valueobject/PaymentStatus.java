package me.namila.food_ordering.common.valueobject;

/**
 * The enum Payment status.
 */
public enum PaymentStatus {
  /**
   * Complete payment status.
   */
  COMPLETE,
  /**
   * Cancelled payment status.
   */
  CANCELLED,
  /**
   * Failed payment status.
   */
  FAILED;

  /**
   * Gets payment status.
   *
   * @param status the status
   * @return the payment status
   */
  public PaymentStatus getPaymentStatus(String status) {
    return switch (status.trim().toLowerCase()) {
      case "complete" -> COMPLETE;
      case "cancelled" -> CANCELLED;
      case "failed" -> FAILED;
      default -> throw new IllegalStateException("No mapped payment status");
    };
  }
}
