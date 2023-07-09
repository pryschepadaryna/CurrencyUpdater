package com.currency.updater.backend.currencyupdater.integration.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(properties = "quartz.enabled=false")
public abstract class BaseServiceIT {

  @Autowired protected EntityManager entityManager;
  @Autowired protected MessageSource messageSource;
}
