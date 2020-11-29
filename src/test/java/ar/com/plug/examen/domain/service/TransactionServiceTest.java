/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.TransactionApiRequest;
import ar.com.plug.examen.app.rest.ClientController;
import ar.com.plug.examen.app.rest.ProductController;
import ar.com.plug.examen.app.rest.TransactionController;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionStatus;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author msulbara
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionController transactionController;

    @Autowired
    private ProductController productController;

    @Autowired
    private ClientController clientController;

    @Test
    @Transactional
    public void create() {
        Client c = new Client();
        c.setName("Jon");
        c.setLastName("Doe");

        Client savedClient = clientController.createClient(c).getBody();
        Product p = new Product();
        p.setName("Product1");
        p.setDescription("this is a prodcut...");
        Product saved = productController.createProduct(p).getBody();

        TransactionApiRequest request = new TransactionApiRequest();
        request.setClientId(1L);
        request.setProductIds(new ArrayList<>(Arrays.asList(1L)));
        request.setStatus(TransactionStatus.PENDING);

        HttpStatus status = transactionController.createTransaction(request).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    @Transactional
    public void get() {
        Transaction t = transactionController.getTransaction(1L).getBody();

        assertNotNull(t);
        assertNotNull(t.getId());
    }

    @Test
    @Transactional
    public void approve() {
        Client c = new Client();
        c.setName("Jon");
        c.setLastName("Doe");

        Client savedClient = clientController.createClient(c).getBody();
        Product p = new Product();
        p.setName("Product2");
        p.setDescription("this is a prodcut...");
        Product saved = productController.createProduct(p).getBody();

        TransactionApiRequest request = new TransactionApiRequest();
        request.setClientId(2L);
        request.setProductIds(new ArrayList<>(Arrays.asList(2L)));
        request.setStatus(TransactionStatus.PENDING);

        transactionController.createTransaction(request);

        HttpStatus status = transactionController.approveTransaction(2L).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }

}
