package com.currency.updater.backend.currencyupdater.service;

import com.currency.updater.backend.currencyupdater.dto.CurrencyRateResponse;
import com.currency.updater.backend.currencyupdater.dto.GetCurrencyRateDto;
import com.currency.updater.backend.currencyupdater.filter.CurrencyRateFilter;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CurrencyRateService {

  /**
   * Updated currency rates in DB based on received from ERP response
   *
   * @param externalApiResponses mapped erp response received from erp
   */
  void updateAll(List<CurrencyRateResponse> externalApiResponses);

  /**
   * Get currency rate by ID
   *
   * @param id - ID of currency rate
   * @return - currency rate entity or throw exception if currency rate not found
   */
  GetCurrencyRateDto getCurrency(Long id);

  /**
   * Return Page of currency rates
   *
   * @param filter - currency rate filter
   * @param pageable - pageable
   * @return - page with currency rate
   */
  Page<GetCurrencyRateDto> getAllRates(final CurrencyRateFilter filter, Pageable pageable);
}
