package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.repository.ClienteRepository;
import ar.com.plug.examen.domain.service.impl.ClienteServiceImpl;
import ar.com.plug.examen.dto.requests.ClienteRequest;
import ar.com.plug.examen.dto.responses.ClienteResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteServiceImpl clienteService = new ClienteServiceImpl();

    @Test
    public void testListClientes() {
        Mockito.when(clienteRepository.findAll()).thenReturn(clienteListMock());
        List<ClienteResponse> expected = clienteService.listClientes();
        Assert.assertEquals(expected, clienteResponseListMock());
    }

    @Test
    public void testAddCliente() {
        Mockito.when(clienteRepository.save(clienteMock())).thenReturn(clienteMock());
        ResponseEntity expected = clienteService.addCliente(clienteRequestMock());
        Assert.assertEquals(expected, ResponseEntity.ok(clienteMock()));
    }

    @Test
    public void testDeleteCliente() {
        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteMock()));
        ResponseEntity expected = clienteService.deleteCliente(1L);
        Assert.assertEquals(expected, ResponseEntity.ok().build());
    }

    @Test
    public void testUpdateCliente() {
        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteMock()));
        Mockito.when(clienteRepository.save(clienteMock())).thenReturn(clienteMock());
        ResponseEntity expected = clienteService.updateCliente(clienteRequestMock(), 1L);
        Assert.assertEquals(expected, ResponseEntity.ok(clienteMock()));
    }

    private List<ClienteResponse> clienteResponseListMock() {
        List<ClienteResponse> clienteResponseList = new ArrayList<>();
        ClienteResponse clienteResponse1 = new ClienteResponse();
        ClienteResponse clienteResponse2 = new ClienteResponse();
        clienteResponse1.setNombre("Juan");
        clienteResponse1.setApellido("Pérez");
        clienteResponse1.setDni("11111111");
        clienteResponse1.setDireccion("Calle Falsa 123");
        clienteResponse1.setEmail("juanperez@mail.com");
        clienteResponse1.setTelefono("1100000000");
        clienteResponseList.add(clienteResponse1);
        clienteResponse2.setNombre("Pepe");
        clienteResponse2.setApellido("García");
        clienteResponse2.setDni("11111112");
        clienteResponse2.setDireccion("Calle Falsa 456");
        clienteResponse2.setEmail("pepegarcia@mail.com");
        clienteResponse2.setTelefono("1100000001");
        clienteResponseList.add(clienteResponse2);
        return clienteResponseList;
    }

    private List<Cliente> clienteListMock() {
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        cliente1.setNombre("Juan");
        cliente1.setApellido("Pérez");
        cliente1.setDni("11111111");
        cliente1.setDireccion("Calle Falsa 123");
        cliente1.setEmail("juanperez@mail.com");
        cliente1.setTelefono("1100000000");
        clientes.add(cliente1);
        cliente2.setNombre("Pepe");
        cliente2.setApellido("García");
        cliente2.setDni("11111112");
        cliente2.setDireccion("Calle Falsa 456");
        cliente2.setEmail("pepegarcia@mail.com");
        cliente2.setTelefono("1100000001");
        clientes.add(cliente2);
        return clientes;
    }

    private Cliente clienteMock() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setDni("11111111");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setEmail("juanperez@mail.com");
        cliente.setTelefono("1100000000");
        return cliente;
    }

    private ClienteRequest clienteRequestMock() {
        ClienteRequest clienteRequest = new ClienteRequest();
        clienteRequest.setNombre("Juan");
        clienteRequest.setApellido("Pérez");
        clienteRequest.setDni("11111111");
        clienteRequest.setDireccion("Calle Falsa 123");
        clienteRequest.setEmail("juanperez@mail.com");
        clienteRequest.setTelefono("1100000000");
        return clienteRequest;
    }
}
