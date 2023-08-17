package me.namila.food_ordering.common.valueobject;

/**
 * The enum Order status.
 */
public enum OrderStatus {
  /**
   * Pending order status.
   */
  PENDING,
  /**
   * Paid order status.
   */
  PAID,
  /**
   * Approved order status.
   */
  APPROVED,
  /**
   * Cancelling order status.
   */
  CANCELLING,
  /**
   * Cancelled order status.
   */
  CANCELLED;

  /**
   * Gets order status.
   *
   * @param status the status
   * @return the order status
   */
  public OrderStatus getOrderStatus(String status) {
    return switch (status.trim().toLowerCase()) {
      case "pending" -> PENDING;
      case "paid" -> PAID;
      case "approved" -> APPROVED;
      case "cancelling" -> CANCELLING;
      case "cancelled" -> CANCELLED;
      default -> throw new IllegalStateException("No mapped order status");
    };
  }
}
