package me.namila.food_ordering.payment.dataaccess.creditentry.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.payment.dataaccess.creditentry.mapper.CreditEntryDataAccessMapper;
import me.namila.food_ordering.payment.dataaccess.creditentry.repository.CreditEntryJpaRepository;
import me.namila.food_ordering.payment.domain.entity.CreditEntry;
import me.namila.food_ordering.payment.domain.ports.output.repository.CreditEntryRepository;

/**
 * The type Credit entry repository.
 */
@Component
public class CreditEntryRepositoryImpl implements CreditEntryRepository {

  private final CreditEntryJpaRepository creditEntryJpaRepository;
  private final CreditEntryDataAccessMapper creditEntryDataAccessMapper;

  /**
   * Instantiates a new Credit entry repository.
   *
   * @param creditEntryJpaRepository    the credit entry jpa repository
   * @param creditEntryDataAccessMapper the credit entry data access mapper
   */
  public CreditEntryRepositoryImpl(CreditEntryJpaRepository creditEntryJpaRepository,
                                   CreditEntryDataAccessMapper creditEntryDataAccessMapper) {
    this.creditEntryJpaRepository = creditEntryJpaRepository;
    this.creditEntryDataAccessMapper = creditEntryDataAccessMapper;
  }

  @Override
  public CreditEntry save(CreditEntry creditEntry) {
    return creditEntryDataAccessMapper.creditEntryEntityToCreditEntry(creditEntryJpaRepository
            .save(creditEntryDataAccessMapper.creditEntryToCreditEntryEntity(creditEntry)));
  }

  @Override
  public Optional<CreditEntry> findByCustomerId(CustomerId customerId) {
    return creditEntryJpaRepository.findByCustomerId(customerId.getBaseId())
            .map(creditEntryDataAccessMapper::creditEntryEntityToCreditEntry);
  }
}
