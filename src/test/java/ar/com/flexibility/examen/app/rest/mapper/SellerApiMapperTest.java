package ar.com.flexibility.examen.app.rest.mapper;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.domain.model.Seller;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SellerApiMapperTest {

    private static final Long DEFAULT_ID = 1L;
    private static final String DEFAULT_DOCUMENT_ID = "123";
    private static final String DEFAULT_NAME = "Manuel";

    private final SellerApiMapper mapper = new SellerApiMapper();

    @Test
    public void buildApiTest() {
        Seller seller = new Seller(DEFAULT_ID, new Date(), DEFAULT_DOCUMENT_ID, DEFAULT_NAME);
        SellerApi result = mapper.buildApi(seller);
        assertNotNull(result);
        assertEquals(DEFAULT_ID, result.getId());
    }

    @Test
    public void buildEntityTest() {
        SellerApi api = new SellerApi(DEFAULT_ID, new Date(), DEFAULT_DOCUMENT_ID, DEFAULT_NAME);
        Seller result = mapper.buildEntity(api);
        assertNotNull(result);
        assertEquals(DEFAULT_ID, result.getId());
    }
}
