package ar.com.plug.examen.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
@Configuration
public class DocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Flexibility challenge Payments API")
                        .description("Challenge de prueba tecnica")
                        .contact(new Contact()
                                .name("Felix Sirit")
                                .email("siritfelix@gmail.com"))
                        .version("1.0"));
    }
}