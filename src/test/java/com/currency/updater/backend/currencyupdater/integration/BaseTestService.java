package com.currency.updater.backend.currencyupdater.integration;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseTestService {

  @Autowired protected EntityManager entityManager;
}
