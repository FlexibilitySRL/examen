package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.enums.TransactionActions;
import ar.com.flexibility.examen.domain.enums.TransactionStatus;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionServiceImplTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TransactionService transactionService;
    private Seller seller;
    private Seller seller2;
    private Product product;
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        product = new Product();
        product.setName("Camera Nikon T50");
        product.setDescription("Camera Nikon Series T50");
        product = entityManager.merge(product);

        seller = new Seller();
        seller.setName("Jose");
        seller = entityManager.merge(seller);

        seller2 = new Seller();
        seller2.setName("withoutT");
        seller2 = entityManager.merge(seller2);

        transaction = new Transaction();
        transaction.setSellerId(seller.getId());
        transaction.setProductId(product.getId());
        transaction.setDate(ZonedDateTime.now());
        transaction.setPrice(292D);
        transaction = entityManager.merge(transaction);
    }

    @Test
    public void list() {
        List<TransactionApi> list = transactionService.list(seller.getId());
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    public void list_empty() {
        List<TransactionApi> list = transactionService.list(seller2.getId());
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test(expected = NotFoundException.class)
    public void list_throw() {
        transactionService.list(seller.getId() * -1);
    }

    @Test
    public void processAction() {
        TransactionApi transactionApi = transactionService.processAction(transaction.getId(), TransactionActions.APPROVE.getCode());
        assertEquals(TransactionStatus.APPROVED.getCode(), transactionApi.getStatus());
    }

    @Test(expected = BadRequestException.class)
    public void processAction_throw() {
        transactionService.processAction(transaction.getId(), "invalid_action");
    }

    @Test
    public void create() {
        TransactionApi transactionApi = new TransactionApi();
        transactionApi.setDate(ZonedDateTime.now());
        transactionApi.setPrice(933D);
        transactionApi.setProductId(product.getId());
        transactionApi.setSellerId(seller.getId());

        TransactionApi created = transactionService.create(transactionApi);
        assertNotNull(created);
    }
}