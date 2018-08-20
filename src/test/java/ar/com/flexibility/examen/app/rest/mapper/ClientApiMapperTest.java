package ar.com.flexibility.examen.app.rest.mapper;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.model.Client;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 19/08/18.
 */
public class ClientApiMapperTest {

    private static final Long DEFAULT_ID = 1L;
    private static final String DEFAULT_DOCUMENT_ID = "123";
    private static final String DEFAULT_NAME = "Manuel";

    private final ClientApiMapper mapper = new ClientApiMapper();

    @Test
    public void buildApiTest() {
        Client client = new Client(DEFAULT_ID, new Date(), DEFAULT_DOCUMENT_ID, DEFAULT_NAME);
        ClientApi result = mapper.buildApi(client);
        assertNotNull(result);
        assertEquals(DEFAULT_ID, result.getId());
    }

    @Test
    public void buildEntityTest() {
        ClientApi api = new ClientApi(DEFAULT_ID, new Date(), DEFAULT_DOCUMENT_ID, DEFAULT_NAME);
        Client result = mapper.buildEntity(api);
        assertNotNull(result);
        assertEquals(DEFAULT_ID, result.getId());
    }
}