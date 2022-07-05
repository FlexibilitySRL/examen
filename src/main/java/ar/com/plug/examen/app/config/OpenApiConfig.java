package ar.com.plug.examen.app.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class OpenApiConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ar.com.plug.examen.app.rest"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Flexibility - Examen",
                "API Payments - Test",
                "API Version 0.0.1",
                "API Terms of Service",
                "mat.matsu89@gmail.com",
                "Matias Matsumoto",
                "https://github.com/mat-matsu/examen"
        );
    }
}
