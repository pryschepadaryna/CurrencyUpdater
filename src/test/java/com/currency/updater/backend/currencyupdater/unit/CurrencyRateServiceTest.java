package com.currency.updater.backend.currencyupdater.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.currency.updater.backend.currencyupdater.dto.CurrencyRateResponse;
import com.currency.updater.backend.currencyupdater.entity.CurrencyRate;
import com.currency.updater.backend.currencyupdater.factory.CurrencyRateResponseObjectMother;
import com.currency.updater.backend.currencyupdater.mapper.CurrencyRateMapper;
import com.currency.updater.backend.currencyupdater.repository.CurrencyRateRepository;
import com.currency.updater.backend.currencyupdater.service.impl.CurrencyRateServiceImpl;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
class CurrencyRateServiceTest {

  @InjectMocks CurrencyRateServiceImpl currencyRateService;
  @Mock private CurrencyRateRepository currencyRateRepository;

  @Spy CurrencyRateMapper rateMapper = Mappers.getMapper(CurrencyRateMapper.class);
  @Captor private ArgumentCaptor<List<CurrencyRate>> savedRateListCaptor;

  @Test
  @Transactional
  void updateAll_emptyDB_ShouldAddAll() throws IOException {
    List<CurrencyRateResponse> externalApiResponse =
        CurrencyRateResponseObjectMother.createDefaultCurrencyRate(
            "src/test/java/com/currency/updater/backend/currencyupdater/resources/responses/standart_external_api_response.json");
    when(currencyRateRepository.findAll()).thenReturn(new ArrayList<>());
    currencyRateService.updateAll(externalApiResponse);
    verify(currencyRateRepository, times(1)).saveAll(savedRateListCaptor.capture());

    List<CurrencyRate> currencyRateList = savedRateListCaptor.getValue();
    assertThat(currencyRateList).hasSize(4);
  }

  @Test
  @Transactional
  void updateAll_withInvalidElement_ShouldThrowJsonMappingException() {
    assertThrows(
        JsonMappingException.class,
        () ->
            CurrencyRateResponseObjectMother.createDefaultCurrencyRate(
                "src/test/java/com/currency/updater/backend/currencyupdater/resources/responses/response_with_invalid_currency_code.json"));
  }
}
