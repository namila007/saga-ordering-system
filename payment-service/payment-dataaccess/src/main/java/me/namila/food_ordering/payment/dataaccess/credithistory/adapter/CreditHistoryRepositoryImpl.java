package me.namila.food_ordering.payment.dataaccess.credithistory.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import me.namila.food_ordering.common.valueobject.CustomerId;
import me.namila.food_ordering.payment.dataaccess.credithistory.entity.CreditHistoryEntity;
import me.namila.food_ordering.payment.dataaccess.credithistory.mapper.CreditHistoryDataAccessMapper;
import me.namila.food_ordering.payment.dataaccess.credithistory.repository.CreditHistoryJpaRepository;
import me.namila.food_ordering.payment.domain.entity.CreditHistory;
import me.namila.food_ordering.payment.domain.ports.output.repository.CreditHistoryRepository;

/**
 * The type Credit history repository.
 */
@Component
public class CreditHistoryRepositoryImpl implements CreditHistoryRepository {

  private final CreditHistoryJpaRepository creditHistoryJpaRepository;
  private final CreditHistoryDataAccessMapper creditHistoryDataAccessMapper;

  /**
   * Instantiates a new Credit history repository.
   *
   * @param creditHistoryJpaRepository    the credit history jpa repository
   * @param creditHistoryDataAccessMapper the credit history data access mapper
   */
  public CreditHistoryRepositoryImpl(CreditHistoryJpaRepository creditHistoryJpaRepository,
                                     CreditHistoryDataAccessMapper creditHistoryDataAccessMapper) {
    this.creditHistoryJpaRepository = creditHistoryJpaRepository;
    this.creditHistoryDataAccessMapper = creditHistoryDataAccessMapper;
  }

  @Override
  public CreditHistory save(CreditHistory creditHistory) {
    return creditHistoryDataAccessMapper
            .creditHistoryEntityToCreditHistory(creditHistoryJpaRepository
                    .save(creditHistoryDataAccessMapper.creditHistoryToCreditHistoryEntity(creditHistory)));
  }

  @Override
  public Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId) {
    Optional<List<CreditHistoryEntity>> creditHistory =
            creditHistoryJpaRepository.findByCustomerId(customerId.getBaseId());
    return creditHistory.map(creditHistoryList -> creditHistoryList.stream()
            .map(creditHistoryDataAccessMapper::creditHistoryEntityToCreditHistory)
            .collect(Collectors.toList()));
  }
}
