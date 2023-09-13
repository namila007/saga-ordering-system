package me.namila.food_ordering.dataaccess.customer.mapper;

import java.util.UUID;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.dataaccess.customer.entity.CustomerEntity;
import me.namila.food_ordering.domain.core.entity.Customer;

/**
 * The interface Customer data access mapper.
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerDataAccessMapper {

  /**
   * To customer entity customer entity.
   *
   * @param customer the customer
   * @return the customer entity
   */
  @Mapping(source = "id.baseId", target = "id")
  CustomerEntity toCustomerEntity(Customer customer);

  /**
   * To customer customer.
   *
   * @param customer the customer
   * @return the customer
   */
  @InheritInverseConfiguration
  @Mapping(target = "id", expression = "java(getCustomerId(customer.getId()))")
  Customer toCustomer(CustomerEntity customer);

  default CustomerId getCustomerId(UUID id) {
    return new CustomerId(id);
  }
}
