package ar.com.plug.examen.domain.integration;

import ar.com.plug.examen.domain.exceptions.EmptyBrandException;
import ar.com.plug.examen.domain.exceptions.EmptyNameException;
import ar.com.plug.examen.domain.exceptions.InvalidPriceException;
import ar.com.plug.examen.domain.exceptions.ProductDoesNotExistException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductServiceImpl service ;

    Product aProductMock;

    @BeforeEach
    public void setUp() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
        aProductMock = new Product("Coca", 125.5, "Coca-Cola");
    }
    @Test
    public void addAProductTest() {
        Product aProductSaved = service.saveProduct(aProductMock);
        assertEquals(aProductSaved.getName(), "Coca" );
        assertEquals(aProductSaved.getBrand(), "Coca-Cola" );
        assertEquals(aProductSaved.getPrice(), 125.5);
    }

    @Test
    public void getProductByIdThrowProductDoesNotExistExceptionWhenTheIdDoesNotExistTest(){
        assertThrows(ProductDoesNotExistException.class, ()-> {
            service.findById(1l);
        });

    }

    @Test
    public void getProductByIdReturnsTheProductWhenAProductExistTest() throws ProductDoesNotExistException {
        Product productSaved = service.saveProduct(aProductMock);
        Product productDB = service.findById(productSaved.getId());

        assertEquals(aProductMock, productDB);
    }

    @Test
    public void updateProductByIdThrowInvalidDataAccessApiUsageExceptionWhenTheIdIsNullTest(){
        assertThrows(InvalidDataAccessApiUsageException.class, ()-> {
            service.updateProduct(aProductMock);
        });
    }

    @Test
    public void updateProductByIdThrowProductDoesNotExistExceptionWhenTheIdDoesNotExistTest(){
        Product mockProduct = mock(Product.class);
        when(mockProduct.getId()).thenReturn(1l);

        assertThrows(ProductDoesNotExistException.class, ()-> {
            service.updateProduct(mockProduct);
        });
    }

    //@Test
    public void updateProductByIdTTest() throws ProductDoesNotExistException, EmptyBrandException, EmptyNameException, InvalidPriceException {
        Product productSaved;
        service.saveProduct(aProductMock);
        Product anotherProduct = new Product("Coca", 150.0, "Coca-Cola");
        productSaved = service.updateProduct(anotherProduct);

        assertEquals(productSaved.getPrice(), 150.0);
        assertEquals(productSaved.getName(), "Coca");
        assertEquals(productSaved.getBrand(), "Coca-Cola");
    }

    @Test
    public void deleteProductByIdTTest() {
        Product productSaved = service.saveProduct(aProductMock);
        service.deleteProduct(productSaved.getId());

        assertThrows(ProductDoesNotExistException.class, ()-> {
            service.findById(productSaved.getId());
        });

    }
}

