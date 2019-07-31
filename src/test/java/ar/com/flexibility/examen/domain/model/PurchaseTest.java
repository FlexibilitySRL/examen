package ar.com.flexibility.examen.domain.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseTest {
    private Purcharse purcharse;

    @Before
    public void initializer() {
        purcharse = new Purcharse();

        Product coca = new Product("Coca", BigDecimal.valueOf(55.50));
        coca.setId(Long.valueOf(1));

        purcharse.add(coca);
    }

    @Test
    public void addProduct_oneProduct() {
        assertEquals(BigDecimal.valueOf(55.50), purcharse.getCost());
        assertNotNull(purcharse.getProducts());
    }

    @Test
    public void deleteProduct_oneProduct() {
        Product product = new Product("Coca", BigDecimal.valueOf(55.50));
        product.setId(Long.valueOf(1));

        purcharse.delete(product);

        assertEquals(0, purcharse.getProducts().size());
        assertEquals(BigDecimal.valueOf(0.0), purcharse.getCost());
    }

    @Test
    public void deleteProduct_withTwoProducts() {
        purcharse.add(new Product("Fanta",BigDecimal.valueOf(60)));

        Product product = new Product("Coca", BigDecimal.valueOf(55.50));
        product.setId(Long.valueOf(1));

        purcharse.delete(product);

        assertEquals(1, purcharse.getProducts().size());
        assertEquals(BigDecimal.valueOf(60.0), purcharse.getCost());
        assertEquals("Fanta", purcharse.getProducts().get(0).getName());
    }
}
