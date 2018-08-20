package ar.com.flexibility.examen.app.rest.mapper;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 19/08/18.
 */
public class ProductApiMapperTest {

    private static final Long DEFAULT_ID = 1L;
    private static final String DEFAULT_NAME = "Switch";

    private final ProductApiMapper mapper = new ProductApiMapper();

    @Test
    public void buildApiTest() {
        Product product = new Product(DEFAULT_ID, new Date(), DEFAULT_NAME, BigDecimal.ONE);
        ProductApi result = mapper.buildApi(product);
        assertNotNull(result);
        assertEquals(DEFAULT_ID, result.getId());
    }

    @Test
    public void buildEntityTest() {
        ProductApi productApi = new ProductApi(DEFAULT_ID, new Date(), DEFAULT_NAME, BigDecimal.ONE);
        Product result = mapper.buildEntity(productApi);
        assertNotNull(result);
        assertEquals(DEFAULT_ID, result.getId());
    }
}