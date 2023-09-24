package me.namila.food_ordering.payment.dataaccess.creditentry.mapper;

import org.springframework.stereotype.Component;

import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.common.valueobject.Money;
import me.namila.food_ordering.payment.dataaccess.creditentry.entity.CreditEntryEntity;
import me.namila.food_ordering.payment.domain.entity.CreditEntry;
import me.namila.food_ordering.payment.domain.valueobject.CreditEntryId;

/**
 * The type Credit entry data access mapper.
 */
@Component
public class CreditEntryDataAccessMapper {

  /**
   * Credit entry entity to credit entry credit entry.
   *
   * @param creditEntryEntity the credit entry entity
   * @return the credit entry
   */
  public CreditEntry creditEntryEntityToCreditEntry(CreditEntryEntity creditEntryEntity) {
    return CreditEntry.Builder.builder().creditEntryId(new CreditEntryId(creditEntryEntity.getId()))
            .customerId(new CustomerId(creditEntryEntity.getCustomerId()))
            .totalCreditAmount(new Money(creditEntryEntity.getTotalCreditAmount())).build();
  }

  /**
   * Credit entry to credit entry entity credit entry entity.
   *
   * @param creditEntry the credit entry
   * @return the credit entry entity
   */
  public CreditEntryEntity creditEntryToCreditEntryEntity(CreditEntry creditEntry) {
    return CreditEntryEntity.builder().id(creditEntry.getId().getBaseId())
            .customerId(creditEntry.getCustomerId().getBaseId())
            .totalCreditAmount(creditEntry.getTotalCreditAmount().getAmount()).build();
  }

}
