package com.currency.updater.backend.currencyupdater.integration.service;

import com.currency.updater.backend.currencyupdater.dto.CurrencyRateResponse;
import com.currency.updater.backend.currencyupdater.entity.CurrencyRate;
import com.currency.updater.backend.currencyupdater.factory.CurrencyRateObjectMother;
import com.currency.updater.backend.currencyupdater.factory.CurrencyRateResponseObjectMother;
import com.currency.updater.backend.currencyupdater.repository.CurrencyRateRepository;
import com.currency.updater.backend.currencyupdater.service.CurrencyRateService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

class CurrencyRateServiceIT extends BaseServiceIT {

  @Autowired CurrencyRateService currencyRateService;
  @Autowired CurrencyRateRepository currencyRateRepository;

  @Test
  @Transactional
  void updateAll_withOneElementForDeletion_ShouldAddAll() throws IOException {
    List<CurrencyRateResponse> externalApiResponse =
        CurrencyRateResponseObjectMother.createDefaultCurrencyRate(
            "src/test/java/com/currency/updater/backend/currencyupdater/resources/responses/standart_external_api_response.json");

    currencyRateRepository.save(CurrencyRateObjectMother.createCurrencyRate("ABC", "ABC"));
    currencyRateService.updateAll(externalApiResponse);
    List<CurrencyRate> updatedCurrencyRateList = currencyRateRepository.findAll();
    Assertions.assertThat(updatedCurrencyRateList).hasSize(4);
    Assertions.assertThat(updatedCurrencyRateList)
        .noneMatch(
            currencyRate ->
                currencyRate.getNameA().equals("ABC") && currencyRate.getNameB().equals("ABC"));
  }

  @Test
  @Transactional
  void updateAll_withOneElementForUpdate_ShouldAddAll() throws IOException {
    List<CurrencyRateResponse> externalApiResponse =
        CurrencyRateResponseObjectMother.createDefaultCurrencyRate(
            "src/test/java/com/currency/updater/backend/currencyupdater/resources/responses/standart_external_api_response.json");
    currencyRateRepository.save(CurrencyRateObjectMother.createCurrencyRate("EUR", "UAH"));
    currencyRateService.updateAll(externalApiResponse);
    List<CurrencyRate> updatedCurrencyRateList =
        currencyRateRepository.findAll(Sort.by(CurrencyRate.Fields.id));
    Assertions.assertThat(updatedCurrencyRateList).hasSize(4);
    CurrencyRate updatedCurrencyRate = updatedCurrencyRateList.get(0);
    Assertions.assertThat(updatedCurrencyRate.getRateToBuy()).isEqualTo(39.85);
    Assertions.assertThat(updatedCurrencyRate.getRateToSell()).isEqualTo(41.0492);
  }

  @Test
  @Transactional
  void updateAll_withEmptyErpResponse_ShouldDoNothing() {
    currencyRateRepository.save(CurrencyRateObjectMother.createCurrencyRate("EUR", "UAH"));
    currencyRateService.updateAll(new ArrayList<>());
    List<CurrencyRate> updatedCurrencyRateList =
        currencyRateRepository.findAll(Sort.by(CurrencyRate.Fields.id));
    Assertions.assertThat(updatedCurrencyRateList).hasSize(1);
    CurrencyRate updatedCurrencyRate = updatedCurrencyRateList.get(0);
    Assertions.assertThat(updatedCurrencyRate.getRateToBuy()).isEqualTo(36.6);
    Assertions.assertThat(updatedCurrencyRate.getRateToSell()).isEqualTo(35.6);
  }
}
