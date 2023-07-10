package com.currency.updater.backend.currencyupdater.unit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

class ArchitectureTest {

  @Test
  void restControllersHasCrossOriginAnnotation() {
    JavaClasses importedClasses =
        new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages("com.currency.updater.backend.currencyupdater");
    classes()
        .that()
        .areAnnotatedWith(RestController.class)
        .or()
        .areAnnotatedWith(Controller.class)
        .should()
        .beAnnotatedWith(CrossOrigin.class)
        .check(importedClasses);
  }
}
