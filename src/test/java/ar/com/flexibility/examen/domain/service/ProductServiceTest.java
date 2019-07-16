package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.service.dto.ProductDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private static final Long DEFAULT_ID = 1L;

    @Mock
    private ProductService productServiceMock;

    @Mock
    private ProductDTO productDTOMock;

    @Test
    public void findAll() {
        ArrayList<ProductDTO> list = new ArrayList<>();
        list.add(productDTOMock);
        when(productServiceMock.findAll()).thenReturn(list);
        assertFalse(ObjectUtils.isEmpty(productServiceMock.findAll()));
    }

    @Test
    public void findAllWithoutResults() {
        when(productServiceMock.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, productServiceMock.findAll().size());
    }

    @Test
    public void findOne() {
        when(productServiceMock.findOne(DEFAULT_ID)).thenReturn(Optional.of(productDTOMock));
        assertTrue(productServiceMock.findOne(DEFAULT_ID).isPresent());
    }

    @Test
    public void findOneNotFound() {
        when(productServiceMock.findOne(DEFAULT_ID)).thenReturn(Optional.empty());
        assertFalse(productServiceMock.findOne(DEFAULT_ID).isPresent());
    }

    @Test
    public void save() {
        when(productServiceMock.save(productDTOMock)).thenReturn(productDTOMock);
        ProductDTO productDTO = productServiceMock.save(productDTOMock);
        assertEquals(productDTO, productDTOMock);
    }

    @Test
    public void delete() {
        productServiceMock.delete(DEFAULT_ID);
        verify(productServiceMock, times(1)).delete(DEFAULT_ID);
    }

    @Test(expected = Exception.class)
    public void deleteNotFound() {
        doThrow(Exception.class).when(productServiceMock).delete(isA(Long.class));
        productServiceMock.delete(new Random().nextLong());
    }

}