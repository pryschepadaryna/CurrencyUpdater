package com.currency.updater.backend.currencyupdater.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.currency.updater.backend.currencyupdater.entity.CurrencyRate;
import com.currency.updater.backend.currencyupdater.factory.CurrencyRateObjectMother;
import com.currency.updater.backend.currencyupdater.repository.CurrencyRateRepository;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

class CurrencyRateRestControllerIT extends BaseControllerIT {
  @Autowired private CurrencyRateRepository currencyRateRepository;

  @Test
  @Transactional
  void getCurrencyRate_DefaultList_ShouldReturnListAccordingToRequest() throws Exception {
    List<CurrencyRate> currencyRateList =
        CurrencyRateObjectMother.createDefaultCurrencyRateDtoList();
    List<CurrencyRate> savedList = currencyRateRepository.saveAll(currencyRateList);
    mockMvc
        .perform(get("/currency-rate/list"))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.data.entities").isNotEmpty())
        .andDo(mvcResult -> assertPageable(mvcResult, 3L, 1, 3L));
    CurrencyRate savedFirst = savedList.get(0);
    mockMvc
        .perform(get("/currency-rate/list?ids=" + savedFirst.getId()))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.data.entities").isNotEmpty())
        .andDo(mvcResult -> assertPageable(mvcResult, 1L, 1, 1L));

    mockMvc
        .perform(get("/currency-rate/list?nameA=USD"))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.data.entities").isNotEmpty())
        .andDo(mvcResult -> assertPageable(mvcResult, 1L, 1, 1L));

    mockMvc
        .perform(get("/currency-rate/list?nameB=UAH"))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.data.entities").isNotEmpty())
        .andDo(mvcResult -> assertPageable(mvcResult, 2L, 1, 2L));
  }

  @Test
  @Transactional
  void getCurrencyRate_DefaultElement_ShouldReturnById() throws Exception {
    CurrencyRate saved =
        currencyRateRepository.save(CurrencyRateObjectMother.createDefaultCurrencyRate());
    mockMvc
        .perform(get("/currency-rate/" + saved.getId()))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("data.id").value(saved.getId()))
        .andExpect(jsonPath("data.nameA").value(saved.getNameA()))
        .andExpect(jsonPath("data.nameB").value(saved.getNameB()))
        .andExpect(jsonPath("data.rateToBuy").value(saved.getRateToBuy()))
        .andExpect(jsonPath("data.rateToSell").value(saved.getRateToSell()))
        .andExpect(jsonPath("data.crossRate").value(saved.getCrossRate()))
        .andExpect(
            jsonPath("data.latestExternalUpdate")
                .value(saved.getLatestExternalUpdate().toInstant(ZoneOffset.UTC).toEpochMilli()));
  }
}
