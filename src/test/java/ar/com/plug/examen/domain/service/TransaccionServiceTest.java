package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.repository.ClienteRepository;
import ar.com.plug.examen.domain.repository.TransaccionRepository;
import ar.com.plug.examen.domain.service.impl.TransaccionServiceImpl;
import ar.com.plug.examen.enums.EstadosTransaccionEnum;
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
public class TransaccionServiceTest {

    @Mock
    private TransaccionRepository transaccionRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private TransaccionServiceImpl transaccionService;

    @Test
    public void testListTransacciones() {
        Mockito.when(transaccionRepository.findAll()).thenReturn(transaccionListMock());
        List<Transaccion> expected = transaccionService.listAllTransacciones();
        Assert.assertEquals(expected, transaccionListMock());
    }

    @Test
    public void testAddTransaccion() {
        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteMock()));
        ResponseEntity expected = transaccionService.startTransaccion(clienteMock().getId());
        Assert.assertEquals(expected, ResponseEntity.ok().build());
    }

    public List<Transaccion> transaccionListMock() {
        List<Transaccion> transaccionList = new ArrayList<>();
        Transaccion transaccion1 = new Transaccion();
        transaccion1.setId(1L);
        transaccion1.setEstado(EstadosTransaccionEnum.EN_PROCESO.name());
        transaccion1.setCliente(clienteMock());
        transaccion1.setTotalTransaccion(1000L);
        Transaccion transaccion2 = new Transaccion();
        transaccion2.setId(2L);
        transaccion2.setEstado(EstadosTransaccionEnum.EN_PROCESO.name());
        transaccion2.setCliente(clienteMock());
        transaccion2.setTotalTransaccion(1000L);
        transaccionList.add(transaccion1);
        transaccionList.add(transaccion2);
        return transaccionList;
    }

    public Transaccion transaccionMock() {
        Transaccion transaccion = new Transaccion();
        transaccion.setId(1L);
        transaccion.setEstado(EstadosTransaccionEnum.EN_PROCESO.name());
        transaccion.setCliente(clienteMock());
        transaccion.setTotalTransaccion(1000L);
        return transaccion;
    }

    private Cliente clienteMock() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setDni("11111111");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setEmail("juanperez@mail.com");
        cliente.setTelefono("1100000000");
        return cliente;
    }

}
