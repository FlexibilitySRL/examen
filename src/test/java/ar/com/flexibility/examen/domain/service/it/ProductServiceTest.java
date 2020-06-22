package ar.com.flexibility.examen.domain.service.it;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
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
public class ProductServiceTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProductService productService;
    private Product product;
    private ProductApi productApiNew;
    private ProductApi productApiUpdated;

    @Before
    public void setUp() {
        product = new Product();
        product.setName("Oven");
        product.setDescription("For baking stuff");
        product = entityManager.merge(product);
        productApiNew = new ProductApi();
        productApiNew.setName("Computer");
        productApiNew.setDescription("For playing games");
        productApiUpdated = new ProductApi(product.getId(), "Laptop", "a small computer");
    }

    @Test
    public void shouldGetAllProducts() {
        List<ProductApi> productList = productService.all();
        assertFalse(productList.isEmpty());
    }

    @Test
    public void shouldGetAProduct() {
        ProductApi productApi = productService.get(product.getId());
        assertNotNull(productApi);
        assertEquals(product.getId(), productApi.getId());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenGettingAProduct() {
        productService.get(-1L);
    }

    @Test
    public void shouldCreateNewProduct() {
        ProductApi created = productService.create(productApiNew);
        assertNotNull(created);
        assertNotNull(created.getId());
    }

    @Test
    public void shouldUpdateProduct() {
        ProductApi updated = productService.update(product.getId(), productApiUpdated);
        assertNotNull(updated);
        assertEquals(productApiUpdated.getId(), updated.getId());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenUpdatingAProduct() {
        productService.update(-1L, productApiNew);
    }

    @Test(expected = NotFoundException.class)
    public void shouldRemoveProduct() {
        productService.remove(productApiUpdated.getId());
        productService.get(productApiUpdated.getId());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundRemovingANotFoundProduct() {
        productService.remove(-1L);
    }
}
