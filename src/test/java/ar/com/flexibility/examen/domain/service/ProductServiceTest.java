package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;
    
    @Test
    public void createProduct()
    {
    	ProductApi productApi = new ProductApi();
    	productApi.setName("Test");
        ProductApi productApiNew = productService.create(productApi);

        assertNotNull(productApiNew);
        assertNotNull(productApiNew.getId());
    }
    
    @Test
    public void getProduct()
    {
    	ProductApi productApi = productService.get(1l);
        assertNotNull(productApi);
    }
    
    @Test
    public void getProducts()
    {
        List<ProductApi> products = productService.getProducts();
        assertNotNull(products);
    }
    
    @Test
    public void updateProduct()
    {
    	ProductApi productApi = new ProductApi();
    	productApi.setName("Test2");
        ProductApi productApiNew = productService.update(1l, productApi);

        assertNotNull(productApiNew);
        assertEquals(productApiNew.getName(), "Test2");
    }
    
    @Test
    public void deleteProduct()
    {
    	productService.delete(1l);
        ProductApi productApi = productService.get(1l);
        assertNull(productApi);
    }
}
