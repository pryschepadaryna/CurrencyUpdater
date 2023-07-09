package com.currency.updater.backend.currencyupdater.controller;

import com.currency.updater.backend.currencyupdater.dto.GetCurrencyRateDto;
import com.currency.updater.backend.currencyupdater.filter.CurrencyRateFilter;
import com.currency.updater.backend.currencyupdater.job.CurrencyRateUpdateJob;
import com.currency.updater.backend.currencyupdater.service.impl.CurrencyRateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Configuration for quartz scheduler for job {@link CurrencyRateUpdateJob} */
@RestController
@RequestMapping(value = "/currency-rate")
@CrossOrigin
@RequiredArgsConstructor
public class CurrencyRateRestController {

  private static final Logger LOG = LoggerFactory.getLogger(CurrencyRateRestController.class);

  private final CurrencyRateServiceImpl currencyRateService;

  @GetMapping("/list")
  public GeneralResponse<PageableWrapper<GetCurrencyRateDto>> getCurrencyRatesList(
      Pageable request, @ModelAttribute final CurrencyRateFilter filter) {
    LOG.debug("Get current rate, filter: {}, pageable: {}", filter, request);
    Page<GetCurrencyRateDto> pageOfDtos = currencyRateService.getAllRates(filter, request);
    return GeneralResponse.ok(PageableWrapper.of(pageOfDtos));
  }

  @GetMapping("/{id}")
  public GeneralResponse<GetCurrencyRateDto> getCurrencyRateById(
      @PathVariable("id") final Long id) {
    LOG.debug("Get current rate with id: {}", id);
    return GeneralResponse.ok(currencyRateService.getCurrency(id));
  }
}
