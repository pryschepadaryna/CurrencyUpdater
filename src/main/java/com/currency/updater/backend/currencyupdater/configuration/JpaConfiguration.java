package com.currency.updater.backend.currencyupdater.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = {
      "com.currency.updater.backend.currencyupdater.repository",
    })
public class JpaConfiguration {}
