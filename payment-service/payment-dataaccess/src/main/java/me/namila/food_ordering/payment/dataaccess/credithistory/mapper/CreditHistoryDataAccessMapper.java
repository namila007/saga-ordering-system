package me.namila.food_ordering.payment.dataaccess.credithistory.mapper;

import org.springframework.stereotype.Component;

import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.payment.dataaccess.credithistory.entity.CreditHistoryEntity;
import me.namila.food_ordering.payment.domain.entity.CreditHistory;
import me.namila.food_ordering.payment.domain.valueobject.CreditHistoryId;

/**
 * The type Credit history data access mapper.
 */
@Component
public class CreditHistoryDataAccessMapper {

  /**
   * Credit history entity to credit history credit history.
   *
   * @param creditHistoryEntity the credit history entity
   * @return the credit history
   */
  public CreditHistory creditHistoryEntityToCreditHistory(CreditHistoryEntity creditHistoryEntity) {
    return CreditHistory.Builder.builder()
            .creditHistoryId(new CreditHistoryId(creditHistoryEntity.getId()))
            .customerId(new CustomerId(creditHistoryEntity.getCustomerId()))
            .amount(new Money(creditHistoryEntity.getAmount()))
            .transactionType(creditHistoryEntity.getType()).build();
  }

  /**
   * Credit history to credit history entity credit history entity.
   *
   * @param creditHistory the credit history
   * @return the credit history entity
   */
  public CreditHistoryEntity creditHistoryToCreditHistoryEntity(CreditHistory creditHistory) {
    return CreditHistoryEntity.builder().id(creditHistory.getId().getBaseId())
            .customerId(creditHistory.getCustomerId().getBaseId())
            .amount(creditHistory.getAmount().getAmount()).type(creditHistory.getTransactionType())
            .build();
  }

}
