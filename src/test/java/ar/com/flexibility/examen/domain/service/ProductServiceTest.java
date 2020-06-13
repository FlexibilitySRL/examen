package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repo.ProductRepository;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    
    @Mock
    ProductRepository repo;
    
    @InjectMocks
    ProductServiceImpl svc;
        
    @Test
    public void createTest() {
        
        Product productNew = new Product();
        productNew.setName("Product Name");
        Product product = new Product();
        product.setName("Product Name");
        product.setId(1L);

        when(repo.save(productNew)).thenReturn(product);

        Product productRes = svc.create(productNew);
        Assert.assertEquals(productNew.getName(), productRes.getName());
    }
    
    @Test
    public void readTest() {
        Product product = new Product();
        product.setId(1L);
        when(repo.findOne(1L)).thenReturn(product);
        
        Product productRead = svc.read(1L);
        Assert.assertEquals(Long.valueOf(1), productRead.getId());
    }    
}
