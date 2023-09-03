package me.namila.food_ordering.domain.application.constant;

/**
 * The type Order service constant.
 */
public class OrderServiceConstant {
  private OrderServiceConstant() {}

  /**
   * The type Kafka topics.
   */
  public static class KafkaTopic {
    /**
     * The constant paymentRequestTopicName.
     */
    public static final String paymentRequestTopicName = "paymentRequestTopicName";
    /**
     * The constant paymentResponseTopicName.
     */
    public static final String paymentResponseTopicName = "paymentResponseTopicName";
    /**
     * The constant restaurantApprovalRequestTopicName.
     */
    public static final String restaurantApprovalRequestTopicName =
        "restaurantApprovalRequestTopicName";
    /**
     * The constant restaurantApprovalResponseTopicName.
     */
    public static final String restaurantApprovalResponseTopicName =
        "restaurantApprovalResponseTopicName";
  }
}
