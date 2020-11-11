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

}
