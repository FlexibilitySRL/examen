package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.repository.ProductRepository;
import ar.com.plug.examen.domain.exceptions.EmptyBrandException;
import ar.com.plug.examen.domain.exceptions.EmptyNameException;
import ar.com.plug.examen.domain.exceptions.InvalidPriceException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTestUsingMock {
    @Mock
    private ProductRepository repository;
    @Mock
    Product aProductMock;

    @InjectMocks
    private ProductServiceImpl service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addAProductTest() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
        Product aProductMock = new Product("Coca", 125.5, "Coca-Cola");

        when(repository.save(any(Product.class))).thenReturn(aProductMock);

        Product aProductSaved = service.saveProduct(aProductMock);

        verify(repository, times(1)).save(aProductMock);

        //esto no tiene setndio, son nulos
        assertEquals(aProductSaved.getBrand(), aProductMock.getBrand());
        assertEquals(aProductSaved.getName(), aProductMock.getName());
        assertEquals(aProductSaved.getPrice(), aProductMock.getPrice());
    }

    /*
    public void getProductByIdTest() throws EmptyBrandException, EmptyNameException, InvalidPriceException, ProductDoesNotExistException {
        when(aProductMock.getBrand()).thenReturn("Coca-Cola");
        when(aProductMock.getName()).thenReturn("Coca");
        when(aProductMock.getPrice()).thenReturn(125.5);

        Optional<Product> aProduct = Optional.of(new Product("Coca", 125.5, "Coca-Cola"));

        when(repository.findById(1l)).thenReturn(aProduct);

        Product productById = service.findById(1L);

        assertEquals(productById.getBrand(), aProductMock.getBrand());
        assertEquals(productById.getName(), aProductMock.getName());
        assertEquals(productById.getPrice(), aProductMock.getPrice());
    }

    */

    public void updateProductTest() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
        Product aProductSaved = new Product("Coca", 140.0, "Coca-Cola");

        //Save the first product to update later
        when(aProductMock.getBrand()).thenReturn("Coca-Cola");
        when(aProductMock.getName()).thenReturn("Coca");
        when(aProductMock.getPrice()).thenReturn(125.5);
        when(repository.save(aProductMock)).thenReturn(aProductMock);

        aProductSaved = service.saveProduct(aProductMock);

        //Create the product to update the first one
        //Optional<Product> productToUpdatePrice = Optional.of(new Product("Coca", 140.0, "Coca-Cola"));
        Product productToUpdatePrice = new Product("Coca", 140.0, "Coca-Cola");

        when(aProductMock.getId()).thenReturn(1l);
        //when(repository.findById(1l)).thenReturn(Optional.of(aProductMock));
        //when(repository.save(any(Product.class))).thenReturn(productToUpdatePrice.get());


        //Update the product
        //aProductSaved = service.updateProduct(productToUpdatePrice);

        assertEquals(aProductSaved.getPrice(), 140.0);
        assertEquals(aProductSaved.getBrand(), aProductMock.getBrand());
        assertEquals(aProductSaved.getName(), aProductMock.getName());


    }


    /*
    public void deleteProductTest() throws EmptyBrandException, EmptyNameException, InvalidPriceException, ProductDoesNotExistException {


        //Product aProductSaved;

        //Save the first product to delete later
        when(aProductMock.getBrand()).thenReturn("Coca-Cola");
        when(aProductMock.getName()).thenReturn("Coca");
        when(aProductMock.getPrice()).thenReturn(125.5);
        when(repository.save(aProductMock)).thenReturn(aProductMock);

        aProductSaved = service.save(aProductMock);

        service.delete(aProductSaved);
        verify(repository, times(1)).delete(aProductMock);

        //assertNull(service.findById(1l));
        //when(repository.findById(1l)).thenReturn(null);


        //First assert no product with id 1 exists.
        assertNull(service.findById(1l));

        Product aProductSaved;
        //Save the first product to delete later
        when(aProductMock.getBrand()).thenReturn("Coca-Cola");
        when(aProductMock.getName()).thenReturn("Coca");
        when(aProductMock.getPrice()).thenReturn(125.5);

        when(repository.save(aProductMock)).thenReturn(aProductMock);
        service.saveProduct(aProductMock);

    }
    */
}
