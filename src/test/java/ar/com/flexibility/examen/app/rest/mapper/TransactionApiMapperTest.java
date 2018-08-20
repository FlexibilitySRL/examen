package ar.com.flexibility.examen.app.rest.mapper;

import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionApiMapperTest {

    private static final Long DEFAULT_ID = 1L;
    private static final Long DEFAULT_TRANSACTION__ID = 1L;
    private static final String DEFAULT_DOCUMENT_ID = "123";
    private static final String DEFAULT_NAME = "Manuel";
    private static final Boolean DEFAULT_APPROVED_FLAG = false;

    private final TransactionApiMapper mapper = new TransactionApiMapper();
    private final Client client = new Client(
            DEFAULT_ID,
            new Date(),
            DEFAULT_DOCUMENT_ID,
            DEFAULT_NAME);
    private final Product product = new Product(
            DEFAULT_ID,
            new Date(),
            DEFAULT_NAME,
            BigDecimal.ONE);

    @Test
    public void buildApiTest() {
        Transaction transaction = new Transaction(
                DEFAULT_ID,
                DEFAULT_TRANSACTION__ID,
                new Date(),
                client,
                product,
                BigDecimal.ONE);
        TransactionApi result = mapper.buildApi(transaction);
        assertNotNull(result);
        assertEquals(DEFAULT_ID, result.getId());
    }

    @Test
    public void buildEntityTest() {
        TransactionApi api = new TransactionApi(DEFAULT_ID,
                DEFAULT_TRANSACTION__ID,
                new Date(),
                client,
                product,
                BigDecimal.ONE,
                DEFAULT_APPROVED_FLAG);
        Transaction result = mapper.buildEntity(api);
        assertNotNull(result);
        assertEquals(DEFAULT_ID, result.getId());
        assertFalse(result.getApproved());
    }
}