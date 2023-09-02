package me.namila.food_ordering.domain.application.config;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.core.env.Environment;

@ConfigurationProperties(prefix = "order-service")
public class OrderServiceConfigData extends Properties {
  private static final String paymentRequestTopicName = "paymentRequestTopicName";
  private static final String paymentResponseTopicName = "paymentResponseTopicName";
  private static final String restaurantApprovalRequestTopicName =
      "restaurantApprovalRequestTopicName";
  private static final String restaurantApprovalResponseTopicName =
      "restaurantApprovalResponseTopicName";
  private final org.springframework.core.env.Environment delegate;


  @ConstructorBinding
  public OrderServiceConfigData(Properties defaults, Environment delegate) {
    super(defaults);
    this.delegate = delegate;
  }

  @Override
  public String getProperty(String key) {
    return delegate.getProperty(key);
  }

  @Override
  public String getProperty(String key, String defaultValue) {
    return delegate.getProperty(key, defaultValue);
  }

}
