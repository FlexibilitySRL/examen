package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.service.ProductoService;
import ar.com.plug.examen.exception.MicroserviceErrorException;
import ar.com.plug.examen.infrastructure.repository.IProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static ar.com.plug.examen.domain.service.DatosProducto.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ProductoServiceImplTest {

    @MockBean
    IProductoRepository iProductoRepository;

    @Autowired
    ProductoService iProductoService;

    @Test
    void testFindProductoById() {

        // Given
        when(iProductoRepository.findById(1)).thenReturn(updateProductoOptional());

        // When
        Producto producto = iProductoService.findProductoById(1);

        // Then
        assertNotNull(producto);
        assertEquals(1, producto.getId());
        assertEquals("Televisor samsung", producto.getNombre());
        assertEquals(1200.00, producto.getPrecio());

        verify(iProductoRepository).findById(anyInt());
    }

    @Test
    void testFindProductoByIdNotFound() {

        // Given
        when(iProductoRepository.findById(1)).thenReturn(Optional.ofNullable(null));

        // When
        Producto producto = iProductoService.findProductoById(1);

        // Then
        assertNull(producto);

        verify(iProductoRepository).findById(anyInt());
    }

    @Test
    void testSaveProducto() throws MicroserviceErrorException {

        // Given
        when(iProductoRepository.save(any())).then(invocation -> {
            Producto p = invocation.getArgument(0);
            p.setId(1);
            return p;
        });

        // When
        Producto producto = iProductoService.saveProducto(createProducto());

        // Then
        assertNotNull(producto);
        assertEquals(1, producto.getId());
        assertEquals("Televisor samsung", producto.getNombre());
        assertEquals(1200.00, producto.getPrecio());

        verify(iProductoRepository).save(any());
    }

    @Test
    void testSaveProductoException() throws Exception {

        // Given
        when(iProductoRepository.save(isNull())).thenReturn(Exception.class);

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            iProductoService.saveProducto(null);
        });

        // Then
        assertEquals(MicroserviceErrorException.class, exception.getClass());

        verify(iProductoRepository, never()).save(isNull());
    }

    @Test
    void testUpdateProducto() throws Exception {

        // Given
        when(iProductoRepository.findById(1)).thenReturn(updateProductoOptional());
        when(iProductoRepository.save(any())).thenReturn(updateProducto());

        // When
        Producto producto = iProductoService.updateProducto(updateProducto(), 1);

        // Then
        assertNotNull(producto);
        assertEquals(1, producto.getId());
        assertEquals("Televisor samsung", producto.getNombre());
        assertEquals(1200.00, producto.getPrecio());

        verify(iProductoRepository).findById(1);
        verify(iProductoRepository).save(any());
    }

    @Test
    void testUpdateProductoException() throws Exception {

        // Given
        when(iProductoRepository.findById(1)).thenReturn(updateProductoOptional());
        when(iProductoRepository.save(isNull())).thenReturn(Exception.class);

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            iProductoService.updateProducto(isNull(), 1);
        });

        // Then
        assertEquals(MicroserviceErrorException.class, exception.getClass());

        verify(iProductoRepository).findById(1);
        verify(iProductoRepository, never()).save(isNull());
    }

    @Test
    void testNotFoundUpdateProducto() throws Exception {

        // Given
        when(iProductoRepository.findById(1)).thenReturn(Optional.ofNullable(null));

        // When
        Producto producto = iProductoService.updateProducto(any(), 1);

        // Then
        assertNull(producto);

        verify(iProductoRepository).findById(1);
    }

    @Test
    void testDeleteProducto() throws Exception {

        // When
        iProductoService.deleteProductoById(anyInt());

        verify(iProductoRepository).deleteById(anyInt());
    }

    @Test
    void testDeleteProductoException() throws Exception {

        // Given
        doThrow(new NullPointerException()).when(iProductoRepository).deleteById(1);

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            iProductoService.deleteProductoById(1);
        });

        // Then
        assertEquals(MicroserviceErrorException.class, exception.getClass());

        verify(iProductoRepository).deleteById(1);
    }

}