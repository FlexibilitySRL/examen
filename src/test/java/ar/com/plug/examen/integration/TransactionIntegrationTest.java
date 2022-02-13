package ar.com.plug.examen.integration;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.rest.ClientController;
import ar.com.plug.examen.app.rest.TransactionController;
import ar.com.plug.examen.app.rest.VendorController;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TransactionIntegrationTest {

    @Autowired
    private TransactionController transactionController;

    @Autowired
    private ClientController clientController;

    @Autowired
    private VendorController vendorController;

    @Test
    public void testCreateTransaction() {
        List<Map<String, String>> products = new ArrayList<>();
        Map<String, String> item = new HashMap<>();
        item.put("product", "11");
        item.put("amount", "10");
        products.add(item);

        TransactionApi request = new TransactionApi(TransactionType.BUY, 1L, 2L, products);
        ResponseEntity response = transactionController.createTransaction(request);
        Transaction transaction = (Transaction) response.getBody();

        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());
    }

    @Test
    public void testCreateTransactionWithInvalidClient() {
        List<Map<String, String>> products = new ArrayList<>();
        Map<String, String> item = new HashMap<>();
        item.put("product", "11");
        item.put("amount", "10");
        products.add(item);

        Long invalidClientId = 4L;
        TransactionApi request = new TransactionApi(TransactionType.BUY, invalidClientId, 2L, products);
        ResponseEntity response = transactionController.createTransaction(request);
        Transaction transaction = (Transaction) response.getBody();

        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());
    }
}
