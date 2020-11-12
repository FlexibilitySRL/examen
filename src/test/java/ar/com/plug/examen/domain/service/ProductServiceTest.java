package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.repository.ProductRepository;
import ar.com.plug.examen.domain.exceptions.EmptyBrandException;
import ar.com.plug.examen.domain.exceptions.EmptyNameException;
import ar.com.plug.examen.domain.exceptions.InvalidPriceException;
import ar.com.plug.examen.domain.exceptions.ProductDoesNotExistException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductServiceImpl service ;

    Product aProductMock;

    @BeforeEach
    public void setUp() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
        aProductMock = new Product("Coca", 125.5, "Coca-Cola");
    }
    @Test
    public void addAProductTest() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
        Product aProductSaved = service.saveProduct(aProductMock);
        assertEquals(aProductSaved.getName(), "Coca" );
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
        Product productSaved = service.saveProduct(aProductMock);
        /*Product anotherProduct = mock(Product.class);
        when(anotherProduct.getId()).thenReturn(1l);
        when(anotherProduct.getPrice()).thenReturn(150.0);
        when(anotherProduct.getName()).thenReturn("Coca");
        when(anotherProduct.getBrand()).thenReturn("Coca-Cola");*/
        Product anotherProduct = new Product("Coca", 150.0, "Coca-Cola");
        productSaved = service.updateProduct(anotherProduct);

        assertEquals(productSaved.getPrice(), 150.0);
        assertEquals(productSaved.getName(), "Coca");
        assertEquals(productSaved.getBrand(), "Coca-Cola");
    }

    @Test
    public void deleteProductByIdTTest() throws ProductDoesNotExistException, EmptyBrandException, EmptyNameException, InvalidPriceException {
        Product productSaved = service.saveProduct(aProductMock);
        service.deleteProduct(productSaved.getId());

        assertThrows(ProductDoesNotExistException.class, ()-> {
            service.findById(productSaved.getId());
        });

    }
}

