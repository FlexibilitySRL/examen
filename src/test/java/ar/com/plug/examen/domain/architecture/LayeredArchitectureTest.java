package ar.com.plug.examen.domain.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.junit.runner.RunWith;

import com.tngtech.archunit.core.importer.ImportOption;

@RunWith(ArchUnitRunner.class) 
@AnalyzeClasses(packages = "ar.com.plug.examen", importOptions = ImportOption.DoNotIncludeTests.class)
public class LayeredArchitectureTest {

	@ArchTest
	static final ArchRule layer_dependencies_are_respected = layeredArchitecture()

			.layer("Controller").definedBy("ar.com.plug.examen.domain.rest")
			.layer("Service").definedBy("ar.com.plug.examen.domain.service.impl")
			.layer("Repository").definedBy("ar.com.plug.examen.domain.repositories")

			.whereLayer("Controller").mayNotBeAccessedByAnyLayer()
			.whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
			.whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");
} 
