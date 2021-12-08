package ar.com.plug.examen.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket api() {
                return new Docket(DocumentationType.SWAGGER_2)
                            .select()
                            .apis(
                                    RequestHandlerSelectors
                                    .basePackage("ar.com.plug.examen.app.rest"))
                            .paths(PathSelectors.any())
                            .build();
        }
	
	@SuppressWarnings("unused")
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Compras API",
				"API Description",
				"1.0",
				"terms",
				new Contact("Compras", "https://compras.com", "apis@compras.com"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList()
				);
	}
}
