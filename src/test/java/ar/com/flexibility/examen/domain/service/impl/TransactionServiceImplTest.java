package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceImplTest {

    @Autowired
    private TransactionServiceImpl authorizationService;
    @Autowired
    private ClientServiceImpl clientService;

    private Purcharse purcharse;
    private Client client;
    private Product product1;
    private Product product2;
    private Transaction transaction;

    @Before
    public void initializr() {
        purcharse = new Purcharse();
        transaction = new Transaction();

        client = new Client("Rapaleta", "Centenario");
        purcharse.add(new Product("Ajedrez", BigDecimal.valueOf(300.99)));
        purcharse.add(new Product("Ping Pong", BigDecimal.valueOf(200)));
        client.addPurcharse(purcharse);
    }

    @Test
    public void addPurcharse_validPurcharse_returnsPurcharse() {
        Client savedClient = clientService.addClient(this.client);

        Transaction savedTransaction = authorizationService.authorize(savedClient.getPurcharses().get(0));

        assertNotNull(savedTransaction);
        assertEquals("APPROVED", savedClient.getPurcharses().get(0).getStatus());
    }

    @Test
    public void findById_withExistingId_returnsSearchedProduct() {
        Transaction savedTransaction = authorizationService.authorize(purcharse);


        Transaction searchedTransaction = authorizationService.findById(savedTransaction.getId());

        assertNotNull(searchedTransaction);
        assertEquals(savedTransaction.getId(), searchedTransaction.getId());
        assertEquals(savedTransaction.getTransactionTime(), searchedTransaction.getTransactionTime());
    }

    @Test
    public void deleteProduct_withExistingProduct() {
        Transaction savedTransaction = authorizationService.authorize(purcharse);

        authorizationService.deleteAuthorization(savedTransaction.getId());

        assertNull(authorizationService.findById(savedTransaction.getId()));
    }
}
