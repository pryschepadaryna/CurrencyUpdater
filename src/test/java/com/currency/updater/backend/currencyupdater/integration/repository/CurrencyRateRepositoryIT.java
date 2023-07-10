package com.currency.updater.backend.currencyupdater.integration.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.currency.updater.backend.currencyupdater.entity.CurrencyRate;
import com.currency.updater.backend.currencyupdater.factory.CurrencyRateObjectMother;
import com.currency.updater.backend.currencyupdater.repository.CurrencyRateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

class CurrencyRateRepositoryIT extends BaseRepositoryIT {
  @Autowired private CurrencyRateRepository currencyRateRepository;

  @Test
  @Transactional
  void saveCurrencyRate() {
    CurrencyRate currencyRate =
        currencyRateRepository.save(CurrencyRateObjectMother.createDefaultCurrencyRate());
    final CurrencyRate foundCurrencyRate =
        currencyRateRepository.findById(currencyRate.getId()).orElseGet(null);
    assertNotNull(foundCurrencyRate);
  }
}
