package ar.com.plug.examen.domain.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;


import javax.persistence.Entity;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

class ArchunitTest {

	JavaClasses importedClasses = new ClassFileImporter().importPath("src/main/java");

	@Test
	public void test_ClassesThatResidesInWebServiceShouldHaveNameMatchingControllerAndBeAnnotatedWithRestController() {
		ArchRule myRule = classes().that().resideInAPackage("ar.com.plug.examen.domain.rest").should()
				.haveSimpleNameEndingWith("Controller").andShould().beAnnotatedWith(RestController.class).andShould()
				.accessClassesThat().areAnnotatedWith(Service.class);
		myRule.check(importedClasses);
	}

	@Test
	public void test_ClassesThatResidesInServiceShouldHaveNameMatchingServiceAndShouldBeAnnotatedWithService() {
		ArchRule myRule = classes().that().resideInAPackage("ar.com.plug.examen.domain.service.impl").should().haveSimpleNameEndingWith("ServiceImpl")
				.andShould().beAnnotatedWith(Service.class).andShould().accessClassesThat()
				.areAnnotatedWith(Service.class).orShould().accessClassesThat().areAnnotatedWith(Repository.class);
		myRule.check(importedClasses);
	}

	@Test
	public void test_ClassesThatResidesInPersistenceShouldHaveNameMatchingRepositoryAndBeAnnotatedWithRepository() {
		ArchRule myRule = classes().that().resideInAPackage("ar.com.plug.examen.domain.repository").should()
				.haveSimpleNameEndingWith("Repository").andShould().beAnnotatedWith(Repository.class);
		myRule.check(importedClasses);
	}

	@Test
	public void test_PersistenceShouldOnlyBeAccessedByServicesAndAccessToOnlyModelOrPersistence() {
		ArchRule myRule = classes().that().resideInAPackage("ar.com.plug.examen.domain.repository").should().onlyBeAccessed().byClassesThat()
				.areAnnotatedWith(Service.class).andShould().onlyAccessClassesThat().areAnnotatedWith(Entity.class)
				.orShould().onlyAccessClassesThat().areAnnotatedWith(Repository.class);
		myRule.check(importedClasses);
	}

	@Test
	public void test_ModelClassesShouldNotAccessToControllerOrServiceOrRepositoryClasses() { 
	ArchRule myRule = classes()
			.that().resideInAPackage("ar.com.plug.examen.domain.model").should().onlyAccessClassesThat().areNotAnnotatedWith(Controller.class)
			.andShould().onlyAccessClassesThat().areNotAnnotatedWith(Service.class).andShould().onlyAccessClassesThat()
			.areNotAnnotatedWith(Repository.class);

	myRule.check(importedClasses);
}
}

