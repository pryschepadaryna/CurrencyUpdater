package com.currency.updater.backend.currencyupdater.repository;

import com.currency.updater.backend.currencyupdater.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository
    extends JpaRepository<CurrencyRate, Long>, JpaSpecificationExecutor<CurrencyRate> {}
