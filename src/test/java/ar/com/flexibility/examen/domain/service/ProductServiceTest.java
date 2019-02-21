/**
 * 
 */
package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.ProductRepository;

/**
 * @author ro
 *
 */
public class ProductServiceTest {
	private ProductService productService;

    @Before
    public void setUp() throws Exception {
        ProductRepository  productRepository = Mockito.mock(ProductRepository.class);
        Seller seller1 = Mockito.mock(Seller.class);
        Mockito.when(productRepository.findAll()).thenReturn(
                Arrays.asList(           
                        new Product(1,"Wood chairs",80D,seller1,10),
                        new Product(2,"Tables",50D,seller1,5)
                )
        );
        productService = new ProductService(productRepository);
    }

    @Test
    public void returnProducts() {

        Collection<Product> products = productService.findAll();
        assertTrue(getproductIds(products).size() == 2);
    }
    
    private List<Long> getproductIds(Collection<Product> products) {
        return products.stream().map(Product::getIdProduct).collect(Collectors.toList());
    }
    
    @Test 
    public void createNewProduct() {
    	Seller seller2 = Mockito.mock(Seller.class);
    	Product product = new Product(3,"Plastic chairs", 50D, seller2, 20);
    	productService.createProduct(product);
    	assertTrue(getproductIds(productService.findAll()).size() == 3);
    }

}
