package ar.com.flexibility.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("ar.com.flexibility.examen")
@EnableSwagger2
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public Docket swaggerApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ar.com.flexibility.examen"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .pathMapping("/");
    }

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("Examen REST API")
                .contact(new Contact("jonatan duplessy",
                        "https://github.com/jonatanduplessy/examen",
                        "jonatan.duplessy@tecso.coop"))
                .build();
    }


}
