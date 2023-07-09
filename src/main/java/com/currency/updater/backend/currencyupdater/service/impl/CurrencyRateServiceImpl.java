package com.currency.updater.backend.currencyupdater.service.impl;

import com.currency.updater.backend.currencyupdater.dto.CurrencyRateResponse;
import com.currency.updater.backend.currencyupdater.dto.GetCurrencyRateDto;
import com.currency.updater.backend.currencyupdater.entity.CurrencyRate;
import com.currency.updater.backend.currencyupdater.exception.EntityNotFoundException;
import com.currency.updater.backend.currencyupdater.filter.CurrencyRateFilter;
import com.currency.updater.backend.currencyupdater.mapper.CurrencyRateMapper;
import com.currency.updater.backend.currencyupdater.repository.CurrencyRateRepository;
import com.currency.updater.backend.currencyupdater.service.CurrencyRateService;
import com.currency.updater.backend.currencyupdater.specification.CurrencyRateSpecification;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {
  private static final Logger LOG = LoggerFactory.getLogger(CurrencyRateServiceImpl.class);
  private final CurrencyRateMapper rateMapper;
  private final CurrencyRateRepository currencyRateRepository;

  @Autowired
  public CurrencyRateServiceImpl(
      CurrencyRateMapper rateMapper, CurrencyRateRepository currencyRateRepository) {
    this.rateMapper = rateMapper;
    this.currencyRateRepository = currencyRateRepository;
  }

  @Override
  public void updateAll(List<CurrencyRateResponse> externalApiResponses) {
    if (CollectionUtils.isEmpty(externalApiResponses)) {
      LOG.info("External API response was empty, no updation needed");
      return;
    }
    LOG.info("Received external API response [{}]", externalApiResponses);
    List<CurrencyRate> existingCurrencyRates = currencyRateRepository.findAll();
    List<CurrencyRate> mappedExternalResponses =
        externalApiResponses.stream().map(rateMapper::map).collect(Collectors.toList());

    if (CollectionUtils.isEmpty(existingCurrencyRates)) {
      currencyRateRepository.saveAll(mappedExternalResponses);
      LOG.info("Successfully saved rates: [{}]", mappedExternalResponses);
      return;
    }

    List<CurrencyRate> existingRatesToDelete =
        existingCurrencyRates.stream()
            .filter(rate -> isRateAbsentInResponse(mappedExternalResponses, rate))
            .toList();

    if (!existingRatesToDelete.isEmpty()) {
      currencyRateRepository.deleteAll(existingRatesToDelete);
      LOG.info("Successfully deleted rates: [{}]", existingRatesToDelete);
      existingCurrencyRates.removeAll(existingRatesToDelete);
    }

    List<CurrencyRate> newRatesToSave =
        mappedExternalResponses.stream()
            .filter(rate -> isRateAbsentInList(existingCurrencyRates, rate))
            .toList();

    if (!newRatesToSave.isEmpty()) {
      currencyRateRepository.saveAll(newRatesToSave);
      LOG.info("Successfully saved rates: [{}]", newRatesToSave);
      mappedExternalResponses.removeAll(newRatesToSave);
    }
    List<CurrencyRate> ratesToUpdate = new ArrayList<>();
    existingCurrencyRates.forEach(
        existingRate -> {
          CurrencyRate matchedRate = findMatchingRate(mappedExternalResponses, existingRate);
          if (matchedRate != null) {
            existingRate.setUpdatedAt(LocalDateTime.now());
            existingRate.setRateToBuy(matchedRate.getRateToBuy());
            existingRate.setRateToSell(matchedRate.getRateToSell());
            existingRate.setCrossRate(matchedRate.getCrossRate());
            ratesToUpdate.add(existingRate);
          }
        });

    currencyRateRepository.saveAll(ratesToUpdate);
    LOG.info("Successfully updated rates: [{}]", ratesToUpdate);
  }

  private boolean isRateAbsentInResponse(
      List<CurrencyRate> mappedExternalResponses, CurrencyRate rate) {
    return mappedExternalResponses.stream()
        .noneMatch(
            response ->
                response.getNameA().equals(rate.getNameA())
                    && response.getNameB().equals(rate.getNameB()));
  }

  private boolean isRateAbsentInList(List<CurrencyRate> currencyRates, CurrencyRate rate) {
    return currencyRates.stream()
        .noneMatch(
            existingRate ->
                existingRate.getNameA().equals(rate.getNameA())
                    && existingRate.getNameB().equals(rate.getNameB()));
  }

  private CurrencyRate findMatchingRate(
      List<CurrencyRate> mappedExternalResponses, CurrencyRate rate) {
    return mappedExternalResponses.stream()
        .filter(
            response ->
                response.getNameA().equals(rate.getNameA())
                    && response.getNameB().equals(rate.getNameB()))
        .findFirst()
        .orElse(null);
  }

  @Override
  public GetCurrencyRateDto getCurrency(Long id) {
    return currencyRateRepository
        .findById(id)
        .map(
            currencyRate -> {
              LOG.info("Retrieved currency rate with ID: [{}]", currencyRate.getId());
              return currencyRate;
            })
        .map(rateMapper::map)
        .orElseThrow(() -> new EntityNotFoundException(id, CurrencyRate.class));
  }

  @Override
  public Page<GetCurrencyRateDto> getAllRates(
      final CurrencyRateFilter filter, final Pageable pageable) {
    final Specification<CurrencyRate> currencyRateSpecification =
        CurrencyRateSpecification.create().filter(filter).build();
    Page<GetCurrencyRateDto> pageOfDtos =
        currencyRateRepository.findAll(currencyRateSpecification, pageable).map(rateMapper::map);
    LOG.info("Got page of Currency rate with size: [{}]", pageOfDtos.getNumberOfElements());
    return pageOfDtos;
  }
}
