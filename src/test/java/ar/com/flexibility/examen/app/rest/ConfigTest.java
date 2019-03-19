package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.service.ProductService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ConfigTest {

    @Bean
    public ProductService productService() {
        return Mockito.mock(ProductService.class);
    }

}
