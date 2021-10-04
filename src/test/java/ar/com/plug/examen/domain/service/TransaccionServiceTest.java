package ar.com.plug.examen.domain.service;

import static org.mockito.Mockito.when;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Compra;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.repository.ClienteRepository;
import ar.com.plug.examen.domain.repository.CompraRepository;
import ar.com.plug.examen.domain.repository.TransaccionRepository;
import ar.com.plug.examen.domain.service.impl.CompraServiceImpl;
import ar.com.plug.examen.domain.service.impl.TransaccionServiceImpl;
import ar.com.plug.examen.dto.responses.ListaComprasResponse;
import ar.com.plug.examen.dto.responses.TransaccionResponse;
import ar.com.plug.examen.enums.EstadosComprasEnum;
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

    @Mock
    private CompraRepository compraRepository;

    @InjectMocks
    private TransaccionServiceImpl transaccionService;

    @InjectMocks
    private CompraServiceImpl compraService;

    @Test
    public void testListAllTransacciones() {
        when(transaccionRepository.findAll()).thenReturn(transaccionListMock());
        List<Transaccion> expected = transaccionService.listAllTransacciones();
        Assert.assertEquals(expected, transaccionListMock());
    }

    @Test
    public void testAddTransaccion() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteMock()));
        ResponseEntity expected = transaccionService.startTransaccion(clienteMock().getId());
        Assert.assertEquals(expected, ResponseEntity.ok().build());
    }

    @Test
    public void testStartTransaccion() {
        Transaccion transaccion = transaccionEnProcesoMock();
        when(clienteRepository.findById(transaccion.getCliente().getId())).thenReturn(Optional.of(clienteMock()));
        ResponseEntity expected = transaccionService.startTransaccion(clienteMock().getId());
        Assert.assertEquals(expected, ResponseEntity.ok().build());
    }

    @Test
    public void testEndTransaccion() {
        List<Compra> compraList = compraListMock();
        Mockito.when(transaccionRepository.findById(1L)).thenReturn(
                Optional.ofNullable(transaccionEnProcesoMock()));
        Mockito.when(compraRepository.findAllByTransaccionId(1L)).thenReturn(compraList);
        TransaccionResponse expected = transaccionService.endTransaccion(1L);
        Assert.assertEquals(expected, transaccionFinalizadaResponseMock());
    }

    private ListaComprasResponse listaCompraResponseMock() {
        ListaComprasResponse listaComprasResponse = new ListaComprasResponse();
        listaComprasResponse.setCompras(compraListMock());
        listaComprasResponse.setTransaccionId(transaccionFinalizadaMock().getId());
        return listaComprasResponse;
    }

    @Test
    public void testSaveTransaccion() {
        Transaccion transaccion = transaccionFinalizadaMock();
        TransaccionResponse transaccionResponse = transaccionFinalizadaResponseMock();
        ResponseEntity expected = transaccionService.saveTransaccion(transaccionResponse,
                transaccion);
    Assert.assertEquals(expected, ResponseEntity.ok().build());
    }

    @Test
    public void testTransaccionActiva() {
        Mockito.when(transaccionRepository.findById(1L)).thenReturn(
                Optional.ofNullable(transaccionEnProcesoMock()));
        Boolean expected = transaccionService.transaccionActivo(1L);
        Assert.assertEquals(expected, true);
    }

    @Test
    public void testTransaccionInactiva() {
        Mockito.when(transaccionRepository.findById(1L)).thenReturn(
                Optional.ofNullable(transaccionFinalizadaMock()));
        Boolean expected = transaccionService.transaccionActivo(1L);
        Assert.assertEquals(expected, false);
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

    public Transaccion transaccionEnProcesoMock() {
        Transaccion transaccion = new Transaccion();
        transaccion.setId(1L);
        transaccion.setEstado(EstadosTransaccionEnum.EN_PROCESO.name());
        transaccion.setCliente(clienteMock());
        transaccion.setTotalTransaccion(1000L);
        return transaccion;
    }

    public Transaccion transaccionFinalizadaMock() {
        Transaccion transaccion = new Transaccion();
        transaccion.setId(1L);
        transaccion.setEstado(EstadosTransaccionEnum.FINALIZADO.name());
        transaccion.setCliente(clienteMock());
        transaccion.setTotalTransaccion(1000L);
        return transaccion;
    }

    public TransaccionResponse transaccionEnProcesoResponseMock() {
        TransaccionResponse transaccionResponse = new TransaccionResponse();
        transaccionResponse.setEstado(EstadosTransaccionEnum.EN_PROCESO.name());
        transaccionResponse.setCliente(clienteMock());
        transaccionResponse.setCompras(compraListMock());
        transaccionResponse.setTotalTransaccion(1000L);
        return transaccionResponse;
    }

    public TransaccionResponse transaccionFinalizadaResponseMock() {
        TransaccionResponse transaccionResponse = new TransaccionResponse();
        transaccionResponse.setIdTransaccion(1L);
        transaccionResponse.setEstado(EstadosTransaccionEnum.FINALIZADO.name());
        transaccionResponse.setCliente(clienteMock());
        transaccionResponse.setCompras(compraListMock());
        transaccionResponse.setTotalTransaccion(3000L);
        return transaccionResponse;
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

    private List<Compra> compraListMock() {
        List<Compra> compras = new ArrayList<>();
        Compra compra1 = new Compra();
        compra1.setId(1L);
        compra1.setProducto(productoMock());
        compra1.setEstado(EstadosComprasEnum.APROBADO.name());
        compra1.setCantidad(1);
        compra1.setTransaccion(transaccionEnProcesoMock());
        compras.add(compra1);
        Compra compra2 = new Compra();
        compra2.setId(2L);
        compra2.setProducto(productoMock());
        compra2.setEstado(EstadosComprasEnum.APROBADO.name());
        compra2.setCantidad(2);
        compra2.setTransaccion(transaccionEnProcesoMock());
        compras.add(compra2);
        return compras;
    }

    private Producto productoMock() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test");
        producto.setDescripcion("Producto de prueba");
        producto.setPrecio(1000L);
        producto.setSku("00000");
        return producto;
    }
}
