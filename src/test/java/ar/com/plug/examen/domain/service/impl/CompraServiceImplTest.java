package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Compra;
import ar.com.plug.examen.domain.service.CompraService;
import ar.com.plug.examen.exception.MicroserviceErrorException;
import ar.com.plug.examen.infrastructure.repository.ICompraRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static ar.com.plug.examen.domain.service.DatosCompra.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CompraServiceImplTest {

    @MockBean
    ICompraRepository iCompraRepository;

    @Autowired
    CompraService iCompraService;

    @Test
    void testFindAllCompras() throws MicroserviceErrorException {

        // Given
        when(iCompraRepository.findAll()).thenReturn(datosCompras());

        // When
        List<Compra> compras = iCompraService.findAll();

        assertFalse(compras.isEmpty());
        assertEquals(1, compras.size());

        verify(iCompraRepository).findAll();
    }

    @Test
    void testFindAllClientesException() throws Exception {

        // Given
        when(iCompraRepository.findAll()).thenThrow(new NullPointerException());

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            iCompraService.findAll();
        });

        // Then
        assertEquals(MicroserviceErrorException.class, exception.getClass());
    }

    @Test
    void testFindCompraById() {

        // Given
        when(iCompraRepository.findById(1)).thenReturn(updateCompraOptional());

        // When
        Compra Compra = iCompraService.findCompraById(1);

        // Then
        assertNotNull(Compra);
        assertEquals(1, Compra.getId());
        assertEquals("Compra prueba", Compra.getDescripcion());
        assertEquals("Compra prueba", Compra.getObservacion());

        verify(iCompraRepository).findById(anyInt());
    }

    @Test
    void testFindCompraByIdNotFound() {

        // Given
        when(iCompraRepository.findById(1)).thenReturn(Optional.ofNullable(null));

        // When
        Compra Compra = iCompraService.findCompraById(1);

        // Then
        assertNull(Compra);

        verify(iCompraRepository).findById(anyInt());
    }

    @Test
    void testSaveCompra() throws MicroserviceErrorException {

        // Given
        when(iCompraRepository.save(any())).then(invocation -> {
            Compra f = invocation.getArgument(0);
            f.setId(1);
            return f;
        });

        // When
        Compra Compra = iCompraService.saveCompra(createCompra());

        // Then
        assertNotNull(Compra);
        assertEquals(1, Compra.getId());
        assertEquals("Compra prueba", Compra.getDescripcion());
        assertEquals("Compra prueba", Compra.getObservacion());

        verify(iCompraRepository).save(any());
    }

    @Test
    void testSaveCompraException() throws Exception {

        // Given
        when(iCompraRepository.save(isNull())).thenReturn(Exception.class);

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            iCompraService.saveCompra(null);
        });

        // Then
        assertEquals(MicroserviceErrorException.class, exception.getClass());

        verify(iCompraRepository, never()).save(isNull());
    }

    @Test
    void testUpdateCompra() throws Exception {

        // Given
        when(iCompraRepository.findById(1)).thenReturn(updateCompraOptional());
        when(iCompraRepository.save(any())).thenReturn(updateCompra());

        // When
        Compra Compra = iCompraService.updateCompra(updateCompra(), 1);

        // Then
        assertNotNull(Compra);
        assertEquals(1, Compra.getId());
        assertEquals("Compra prueba", Compra.getDescripcion());
        assertEquals("Compra prueba", Compra.getObservacion());

        verify(iCompraRepository).findById(1);
        verify(iCompraRepository).save(any());
    }

    @Test
    void testUpdateCompraException() throws Exception {

        // Given
        when(iCompraRepository.findById(1)).thenReturn(updateCompraOptional());
        when(iCompraRepository.save(isNull())).thenReturn(Exception.class);

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            iCompraService.updateCompra(isNull(), 1);
        });

        // Then
        assertEquals(MicroserviceErrorException.class, exception.getClass());

        verify(iCompraRepository).findById(1);
        verify(iCompraRepository, never()).save(isNull());
    }

    @Test
    void testNotFoundUpdateCompra() throws Exception {

        // Given
        when(iCompraRepository.findById(1)).thenReturn(Optional.ofNullable(null));

        // When
        Compra compra = iCompraService.updateCompra(any(), 1);

        // Then
        assertNull(compra);

        verify(iCompraRepository).findById(1);
    }

    @Test
    void testDeleteCompra() throws Exception {

        // When
        iCompraService.deleteCompraById(anyInt());

        verify(iCompraRepository).deleteById(anyInt());
    }

    @Test
    void testDeleteCompraException() throws Exception {

        // Given
        doThrow(new NullPointerException()).when(iCompraRepository).deleteById(1);

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            iCompraService.deleteCompraById(1);
        });

        // Then
        assertEquals(MicroserviceErrorException.class, exception.getClass());

        verify(iCompraRepository).deleteById(1);
    }

    @Test
    void testAprobarCompra() throws Exception {

        // Given
        when(iCompraRepository.findById(1)).thenReturn(updateCompraOptional());
        when(iCompraRepository.save(any())).thenReturn(updateCompra());

        // When
        Compra compra = iCompraService.aprobarCompra(1);

        // Then
        assertNotNull(compra);
        assertEquals(1, compra.getId());
        assertEquals("Compra prueba", compra.getDescripcion());
        assertEquals("Compra prueba", compra.getObservacion());

        verify(iCompraRepository).findById(1);
        verify(iCompraRepository).save(any());
    }

    @Test
    void testAprobarCompraException() throws Exception {

        // Given
        when(iCompraRepository.findById(1)).thenReturn(null);
        when(iCompraRepository.save(isNull())).thenReturn(Exception.class);

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            iCompraService.aprobarCompra(1);
        });

        // Then
        assertEquals(MicroserviceErrorException.class, exception.getClass());

        verify(iCompraRepository).findById(1);
        verify(iCompraRepository, never()).save(isNull());
    }

    @Test
    void testNotFoundAprobarCompra() throws Exception {

        // Given
        when(iCompraRepository.findById(1)).thenReturn(Optional.ofNullable(null));

        // When
        Compra compra = iCompraService.aprobarCompra(1);

        // Then
        assertNull(compra);

        verify(iCompraRepository).findById(1);
    }
}