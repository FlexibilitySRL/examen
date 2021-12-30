package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.service.ClienteService;
import ar.com.plug.examen.exception.MicroserviceErrorException;
import ar.com.plug.examen.infrastructure.repository.IClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static ar.com.plug.examen.domain.service.DatosCliente.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteServiceImplTest {

    @MockBean
    IClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

    @Test
    void testFindAll() throws MicroserviceErrorException {

        // Given
        when(clienteRepository.findAll()).thenReturn(datosCliente());

        // When
        List<Cliente> clientes = clienteService.findAll();

        assertFalse(clientes.isEmpty());
        assertEquals(2, clientes.size());

        verify(clienteRepository).findAll();
    }

    @Test
    void testFindAllClientesException() throws Exception {

        // Given
        when(clienteRepository.findAll()).thenThrow(new NullPointerException());

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            clienteService.findAll();
        });

        // Then
        assertEquals(MicroserviceErrorException.class, exception.getClass());
    }

    @Test
    void testFindClienteById() {

        // Given
        when(clienteRepository.findById(1)).thenReturn(updateClienteOptional());

        // When
        Cliente cliente = clienteService.findById(1);

        // Then
        assertNotNull(cliente);
        assertEquals(1, cliente.getId());
        assertEquals("Carlos", cliente.getNombre());
        assertEquals("Borre", cliente.getApellido());
        assertEquals("cborre@gmail.com", cliente.getEmail());

        verify(clienteRepository).findById(1);

    }

    @Test
    void testFindClienteByIdNotFound() {

        // Given
        when(clienteRepository.findById(1)).thenReturn(Optional.ofNullable(null));

        // When
        Cliente cliente = clienteService.findById(1);

        // Then
        assertNull(cliente);

        verify(clienteRepository).findById(1);
    }

    @Test
    void testSaveCliente() throws Exception {

        // Given
        when(clienteRepository.save(any())).then(invocation -> {
            Cliente c = invocation.getArgument(0);
            c.setId(1);
            return c;
        });

        // When
        Cliente cliente = clienteService.save(createCliente());

        // Then
        assertNotNull(cliente);
        assertEquals(1, cliente.getId());
        assertEquals("Carlos", cliente.getNombre());
        assertEquals("Borre", cliente.getApellido());
        assertEquals("cborre@gmail.com", cliente.getEmail());

        verify(clienteRepository).save(any());
    }

    @Test
    void testSaveClienteException() throws Exception {

        // Given
        when(clienteRepository.save(isNull())).thenReturn(Exception.class);

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            clienteService.save(null);
        });

        // Then
        assertEquals(MicroserviceErrorException.class, exception.getClass());

        verify(clienteRepository, never()).save(isNull());
    }

    @Test
    void testUpdateCliente() throws Exception {

        // Given
        when(clienteRepository.findById(1)).thenReturn(updateClienteOptional());
        when(clienteRepository.save(any())).thenReturn(updateCliente());

        // When
        Cliente cliente = clienteService.update(updateCliente(), 1);

        // Then
        assertNotNull(cliente);
        assertEquals(1, cliente.getId());
        assertEquals("Carlos", cliente.getNombre());
        assertEquals("Borre", cliente.getApellido());
        assertEquals("cborre@gmail.com", cliente.getEmail());

        verify(clienteRepository).findById(1);
        verify(clienteRepository).save(any());
    }

    @Test
    void testUpdateTutorialException() throws Exception {

        // Given
        when(clienteRepository.findById(1)).thenReturn(updateClienteOptional());
        when(clienteRepository.save(isNull())).thenReturn(Exception.class);

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            clienteService.update(isNull(), 1);
        });

        // Then
        assertEquals(MicroserviceErrorException.class, exception.getClass());

        verify(clienteRepository).findById(1);
        verify(clienteRepository, never()).save(isNull());
    }

    @Test
    void testNotUpdateTutorial() throws Exception {

        // Given
        when(clienteRepository.findById(1)).thenReturn(Optional.ofNullable(null));

        // When
        Cliente cliente = clienteService.update(any(), 1);

        // Then
        assertNull(cliente);

        verify(clienteRepository).findById(1);
    }

    @Test
    void testDeleteCliente() throws Exception {

        // When
        clienteService.delete(anyInt());

        verify(clienteRepository).deleteById(anyInt());
    }

    @Test
    void testDeleteClienteException() throws Exception {

        // Given
        doThrow(new NullPointerException()).when(clienteRepository).deleteById(1);

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            clienteService.delete(1);
        });

        // Then
        assertEquals(MicroserviceErrorException.class, exception.getClass());

        verify(clienteRepository).deleteById(1);
    }
}