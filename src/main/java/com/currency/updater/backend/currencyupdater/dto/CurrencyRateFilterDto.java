package com.currency.updater.backend.currencyupdater.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Filter DTO for searching {@link com.currency.updater.backend.currencyupdater.entity.CurrencyRate}
 * by JPA specification
 */
@Setter
@Getter
@ToString
public class CurrencyRateFilterDto {
  private List<Long> ids;

  private Long latestExternalUpdateFrom;
  private Long latestExternalUpdateTo;

  private String nameA;
  private String nameB;
}
