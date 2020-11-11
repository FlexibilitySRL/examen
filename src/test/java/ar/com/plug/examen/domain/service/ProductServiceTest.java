package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.repository.ProductRepository;
import ar.com.plug.examen.domain.exceptions.EmptyBrandException;
import ar.com.plug.examen.domain.exceptions.EmptyNameException;
import ar.com.plug.examen.domain.exceptions.InvalidPriceException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repository;

    @Mock
    private Product aProductMock;

    @Test
    public void addAProductTest() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
        when(aProductMock.getBrand()).thenReturn("Coca-Cola");
        when(aProductMock.getName()).thenReturn("Coca");
        when(aProductMock.getPrice()).thenReturn(125.5);

        Product aProductSaved;
        when(repository.save(aProductMock)).thenReturn(aProductMock);

        aProductSaved = service.save(aProductMock);
        verify(repository, times(1)).save(aProductMock);

        assertEquals(aProductSaved.getBrand(), aProductMock.getBrand());
        assertEquals(aProductSaved.getName(), aProductMock.getName());
        assertEquals(aProductSaved.getPrice(), aProductMock.getPrice());
    }

    @Test
    public void getProductByIdTest() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
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


    public void updateProductTest() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
        Product aProductSaved;

        /*Save the first product to update later*/
        when(aProductMock.getBrand()).thenReturn("Coca-Cola");
        when(aProductMock.getName()).thenReturn("Coca");
        when(aProductMock.getPrice()).thenReturn(125.5);
        when(repository.save(aProductMock)).thenReturn(aProductMock);

        aProductSaved = service.save(aProductMock);

        /*Create the product to update the first one*/
        Optional<Product> productToUpdatePrice = Optional.of(new Product("Coca", 140.0, "Coca-Cola"));
        when(repository.findById(1l)).thenReturn(productToUpdatePrice);
        when(aProductMock.getId()).thenReturn(1l);
        when(repository.save(any(Product.class))).thenReturn(productToUpdatePrice.get());


        /*Update the product*/
        aProductSaved = service.updateProduct(productToUpdatePrice.get());

        assertEquals(aProductSaved.getPrice(), 140.0);
        assertEquals(aProductSaved.getBrand(), aProductMock.getBrand());
        assertEquals(aProductSaved.getName(), aProductMock.getName());

    }

    
    public void deleteProductTest() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
       /*
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
        */

        //First assert no product with id 1 exists.
        assertNull(service.findById(1l));

        Product aProductSaved;
        //Save the first product to delete later
        when(aProductMock.getBrand()).thenReturn("Coca-Cola");
        when(aProductMock.getName()).thenReturn("Coca");
        when(aProductMock.getPrice()).thenReturn(125.5);

        when(repository.save(aProductMock)).thenReturn(aProductMock);
        service.save(aProductMock);


    }

}

