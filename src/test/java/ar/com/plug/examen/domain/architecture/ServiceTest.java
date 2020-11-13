package ar.com.plug.examen.domain.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AnalyzeClasses(packages ="ar.com.plug.examen.domain.service")
public class ServiceTest {

    @ArchTest
    ArchRule service_impl_should_be_suffixed = ArchRuleDefinition.classes()
            .that().resideInAPackage("..service.impl..")
            .should().haveSimpleNameEndingWith("ServiceImpl");

    @ArchTest
    ArchRule service_should_be_suffixed = ArchRuleDefinition.classes()
            .that().resideInAPackage("..service.")
            .should().haveSimpleNameEndingWith("Service");

    @Test
    public void serviceClassesShouldBeAnnotatedWithServiceAndTransactionalAnnotation() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("ar.com.plug.examen");

        ArchRule rule = ArchRuleDefinition.classes()
                .that().haveSimpleNameEndingWith("ServiceImpl")
                .should().beAnnotatedWith(Transactional.class)
                .andShould().beAnnotatedWith(Service.class);

        rule.check(importedClasses);
    }
}
