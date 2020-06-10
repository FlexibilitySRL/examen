package ar.com.flexibility.examen.domain.repositories;

import ar.com.flexibility.examen.domain.model.Seller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SellerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private SellerRepository sellerRepository;
    private Seller seller;

    @Before
    public void setup() {
        seller = new Seller();
        seller.setName("Jose");

        seller = entityManager.merge(seller);
    }

    @Test
    public void create() {
        seller = sellerRepository.save(seller);
        assertNotNull(seller.getId());
    }

    @Test
    public void update() {
        Seller sellerToUpdate = new Seller();
        sellerToUpdate.setName("Maria");

        Seller updated = sellerRepository.save(sellerToUpdate);
        assertNotNull(updated);
        assertEquals(sellerToUpdate.getId(), updated.getId());
    }

    @Test
    public void retrieve() {
        Seller retrieved = sellerRepository.findById(seller.getId()).orElse(null);
        assertNotNull(retrieved);
        assertNotNull(retrieved.getId());
        assertEquals(seller.getId(), retrieved.getId());
    }

    @Test
    public void list() {
        List<Seller> lists = sellerRepository.findAll();
        assertNotNull(lists);
        assertFalse(lists.isEmpty());
    }

    @Test
    public void delete() {
        Seller seller = new Seller();
        seller.setName("Jose");
        seller = entityManager.merge(seller);

        sellerRepository.delete(seller.getId());

        assertFalse(sellerRepository.findById(seller.getId()).isPresent());
    }

}