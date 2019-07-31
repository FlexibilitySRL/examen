package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purcharse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurcharseServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private PurcharseServiceImpl purcharseService;
    private Purcharse purcharse;
    private Product product1;
    private Product product2;

    @Before
    public void initializer() {
        purcharse = new Purcharse();
        product1 = new Product("Cheddar", BigDecimal.valueOf(150.25));
        product2 = new Product("Shampoo", BigDecimal.valueOf(130));

        purcharse.add(product1);
        purcharse.add(product2);
    }

    @Test
    public void addPurcharse_withValidsProducs_returnsPurcharse() {
        Purcharse savedPurcharse = purcharseService.addPurcharse(this.purcharse);

        assertNotNull(savedPurcharse);
        assertEquals(2, savedPurcharse.getProducts().size());
        assertEquals("Cheddar", savedPurcharse.getProducts().get(0).getName());
    }

    @Test
    public void addProductToPurcharse_existingProduct_returnsUpdatedPurcharse() {
        Product savedProduct = productService.addProduct(new Product("Coca", BigDecimal.valueOf(75.00)));
        Purcharse savedPurcharse = purcharseService.addPurcharse(this.purcharse);

        Purcharse purcharseWithNewProduct = purcharseService.addProduct(savedPurcharse, savedProduct);

        assertNotNull(purcharseWithNewProduct);
        assertEquals(3, purcharseWithNewProduct.getProducts().size());
        assertEquals(savedProduct.getId(), purcharseWithNewProduct.getProducts().get(2).getId());
        assertEquals(savedProduct.getName(), purcharseWithNewProduct.getProducts().get(2).getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addProductToPurcharse_inexistingProduct_returnIllegalArgumentException(){
        Purcharse savedPurcharse = purcharseService.addPurcharse(this.purcharse);

        Purcharse purcharseWithNewProduct = purcharseService.addProduct(savedPurcharse, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addProductToPurcharset_inexistingPurcharse_returnIllegalArgumentException(){
        Product savedProduct = productService.addProduct(new Product("Coca", BigDecimal.valueOf(75)));

        Purcharse purcharseWithNewProduct = purcharseService.addProduct(null, savedProduct);
    }

    @Test
    public void updatePucharse_wihtValidProducProducs_returnsUpdatedPurcharse() {
        Purcharse savedPurcharse = purcharseService.addPurcharse(this.purcharse);
        savedPurcharse.getProducts().get(0).setName("Mayonesa");
        savedPurcharse.getProducts().get(1).setPrice(BigDecimal.valueOf(120));
        Purcharse updatedPurcharse = purcharseService.updatePurcharse(savedPurcharse);

        assertNotNull(updatedPurcharse);
        assertEquals(2, updatedPurcharse.getProducts().size());
        assertEquals("Mayonesa", updatedPurcharse.getProducts().get(0).getName());
        assertEquals(BigDecimal.valueOf(120), updatedPurcharse.getProducts().get(1).getPrice());
    }

    @Test
    public void findById_withExistingId_returnsPurcharse() {
        Purcharse savedPurcharse = purcharseService.addPurcharse(this.purcharse);

        Purcharse searchedPurcharse = purcharseService.findById(savedPurcharse.getId());

        assertNotNull(searchedPurcharse);
        assertEquals(2, searchedPurcharse.getProducts().size());
        assertEquals("Cheddar", searchedPurcharse.getProducts().get(0).getName());
    }

    @Test
    public void deleteShoppingList_withExistingProduct() {
        Purcharse savedPurcharse = purcharseService.addPurcharse(this.purcharse);

        purcharseService.deletePurcharse(savedPurcharse.getId());

        assertNull(purcharseService.findById(savedPurcharse.getId()));
    }

}
