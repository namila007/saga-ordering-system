package me.namila.food_ordering.payment.domain.constant;

/**
 * The type Order service constant.
 */
public class PaymentServiceConstant {
  private PaymentServiceConstant() {
  }

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

  }
}
