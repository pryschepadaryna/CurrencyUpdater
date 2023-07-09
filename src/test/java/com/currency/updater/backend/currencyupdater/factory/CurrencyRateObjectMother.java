package com.currency.updater.backend.currencyupdater.factory;

import com.currency.updater.backend.currencyupdater.entity.CurrencyRate;
import java.time.LocalDateTime;
import java.util.List;

public class CurrencyRateObjectMother {

  public static CurrencyRate createDefaultCurrencyRate() {
    return CurrencyRate.builder()
        .latestExternalUpdate(LocalDateTime.now())
        .nameA("EUR")
        .nameB("UAH")
        .rateToBuy(36.6)
        .rateToSell(35.6)
        .crossRate(0.0)
        .build();
  }

  public static CurrencyRate createCurrencyRate(String nameA, String nameB) {
    return CurrencyRate.builder()
        .latestExternalUpdate(LocalDateTime.now())
        .nameA(nameA)
        .nameB(nameB)
        .rateToBuy(36.6)
        .rateToSell(35.6)
        .crossRate(0.0)
        .build();
  }

  public static List<CurrencyRate> createDefaultCurrencyRateDtoList() {
    return List.of(
        CurrencyRate.builder()
            .id(1L)
            .latestExternalUpdate(LocalDateTime.now())
            .nameA("USD")
            .nameB("UAH")
            .rateToBuy(36.6)
            .rateToSell(35.6)
            .crossRate(0.0)
            .build(),
        CurrencyRate.builder()
            .id(2L)
            .latestExternalUpdate(LocalDateTime.now())
            .nameA("EUR")
            .nameB("UAH")
            .rateToBuy(34.6)
            .rateToSell(35.6)
            .crossRate(0.0)
            .build(),
        CurrencyRate.builder()
            .id(3L)
            .latestExternalUpdate(LocalDateTime.now())
            .nameA("EUR")
            .nameB("USD")
            .rateToBuy(2.6)
            .rateToSell(3.6)
            .crossRate(0.0)
            .build());
  }
}
