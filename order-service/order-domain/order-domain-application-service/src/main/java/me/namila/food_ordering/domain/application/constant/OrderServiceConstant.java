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
    public static final String paymentRequestTopicName = "payment-request-topic-name";
    /**
     * The constant paymentResponseTopicName.
     */
    public static final String paymentResponseTopicName = "payment-response-topic-name";
    /**
     * The constant restaurantApprovalRequestTopicName.
     */
    public static final String restaurantApprovalRequestTopicName =
        "restaurant-approval-request-topic-name";
    /**
     * The constant restaurantApprovalResponseTopicName.
     */
    public static final String restaurantApprovalResponseTopicName =
        "restaurant-approval-response-topic-name";
  }
}
