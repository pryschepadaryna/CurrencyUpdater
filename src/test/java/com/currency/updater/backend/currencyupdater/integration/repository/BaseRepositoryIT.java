package com.currency.updater.backend.currencyupdater.integration.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration.FlywayConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = false)
@ImportAutoConfiguration({FlywayConfiguration.class})
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public abstract class BaseRepositoryIT {

  @Autowired protected EntityManager entityManager;
}
