package com.currency.updater.backend.currencyupdater.filter;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Filter for searching {@link com.currency.updater.backend.currencyupdater.entity.CurrencyRate} by
 * JPA specification
 */
@Setter
@Getter
@ToString
public class CurrencyRateFilter {
  private List<Long> ids;

  private LocalDateTime latestExternalUpdateFrom;
  private LocalDateTime latestExternalUpdateTo;

  private String nameA;
  private String nameB;
}
