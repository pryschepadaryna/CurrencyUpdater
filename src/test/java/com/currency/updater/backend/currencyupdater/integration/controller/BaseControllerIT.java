package com.currency.updater.backend.currencyupdater.integration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.currency.updater.backend.currencyupdater.controller.PageableWrapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "quartz.enabled=false")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseControllerIT {

  protected MockMvc mockMvc;
  @Autowired protected ObjectMapper objectMapper;
  @Autowired protected WebApplicationContext wac;

  @PostConstruct
  public void init() {
    objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  protected void assertPageable(
      final MvcResult mvcResult,
      final long totalElements,
      final long totalPages,
      final long entitiesSize)
      throws Exception {
    final JsonNode root = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
    final JsonNode path = root.path("data");
    final PageableWrapper pageableWrapper = objectMapper.convertValue(path, PageableWrapper.class);
    assertNotNull(pageableWrapper);
    assertEquals(totalElements, pageableWrapper.getTotalElements().longValue());
    assertEquals(totalPages, pageableWrapper.getTotalPages().intValue());
    assertEquals(entitiesSize, pageableWrapper.getEntities().size());
  }
}
