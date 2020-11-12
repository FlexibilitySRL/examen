package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.repository.ProductRepository;
import ar.com.plug.examen.domain.exceptions.ProductDoesNotExistException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceUnitTest {
    @Mock
    private ProductRepository repository;
    @Mock
    Product aProductMock;

    @InjectMocks
    private ProductServiceImpl service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aProductMock = mock(Product.class);
    }

    @Test
    public void addAProductTest(){
        when(repository.save(any(Product.class))).thenReturn(aProductMock);
        service.saveProduct(aProductMock);
        verify(repository, times(1)).save(aProductMock);

    }

    @Test
    public void getProductByIdTest() throws ProductDoesNotExistException {
        when(repository.findById(1l)).thenReturn(Optional.of(aProductMock));
        service.findById(1l);
        verify(repository, times(1)).findById(1l);
    }

    @Test
    public void updateProductTest() throws ProductDoesNotExistException {
        when(repository.findById(1l)).thenReturn(Optional.of(aProductMock));
        when(aProductMock.getId()).thenReturn(1l);

        service.updateProduct(aProductMock);
        verify(repository, times(1)).findById(1l);
        verify(repository, times(1)).save(aProductMock);
    }

    @Test
    public void deleteProductTest(){
        when(repository.findById(1l)).thenReturn(Optional.of(aProductMock));

        service.deleteProduct(1l);
        verify(repository, times(1)).delete(aProductMock);
    }

}
