package ar.com.flexibility.examen.domain.repositories;

import ar.com.flexibility.examen.domain.enums.TransactionStatus;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.model.Transaction;
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
public class TransactionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TransactionRepository transactionRepository;
    private Transaction transaction;
    private Seller seller;
    private Product product;

    @Before
    public void setup() {
        seller = new Seller();
        seller.setName("Jose");
        seller = entityManager.merge(seller);

        product = new Product();
        product.setName("Camera Nikon T50");
        product.setDescription("Camera Nikon Series T50");
        product = entityManager.merge(product);

        transaction = new Transaction();
        transaction.setProductId(product.getId());
        transaction.setSellerId(seller.getId());
        transaction.setDate(ZonedDateTime.now());
        transaction.setPrice(323D);
        transaction = entityManager.merge(transaction);
    }

    @Test
    public void getAllTransactions() {
        List<Transaction> list = transactionRepository.findBySellerId(seller.getId());
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(transaction.getId(), list.get(0).getId());
    }

    @Test
    public void getAllTransactions_empty() {
        List<Transaction> list = transactionRepository.findBySellerId(seller.getId() + 1);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void updateTransaction() {
        Transaction recovered = transactionRepository.findById(transaction.getId()).orElse(null);
        assertNotNull(recovered);
        assertEquals(TransactionStatus.PENDING.getCode(), recovered.getStatus());

        recovered.setStatus(TransactionStatus.APPROVED.getCode());
        Transaction updated = transactionRepository.save(transaction);
        assertNotNull(updated);
        assertNotNull(updated.getId());
        assertEquals(TransactionStatus.APPROVED.getCode(), updated.getStatus());
        assertEquals(recovered.getId(), updated.getId());
    }

}