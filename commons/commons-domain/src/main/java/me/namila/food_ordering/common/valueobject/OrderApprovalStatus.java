package me.namila.food_ordering.common.valueobject;

/**
 * The enum Payment status.
 */
public enum OrderApprovalStatus {
  /**
   * Complete payment status.
   */
  APPROVED,
  /**
   * Cancelled payment status.
   */
  REJECTED;

  /**
   * Gets payment status.
   *
   * @param status the status
   * @return the payment status
   */
  public OrderApprovalStatus getOrderApprovalStatus(String status) {
    return switch (status.trim().toLowerCase()) {
      case "approved" -> APPROVED;
      case "rejected" -> REJECTED;
      default -> throw new IllegalStateException("No mapped Order Approval status");
    };
  }
}
