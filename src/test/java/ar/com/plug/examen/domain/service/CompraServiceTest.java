package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Compra;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.repository.CompraRepository;
import ar.com.plug.examen.domain.repository.ProductoRepository;
import ar.com.plug.examen.domain.repository.TransaccionRepository;
import ar.com.plug.examen.domain.service.impl.CompraServiceImpl;
import ar.com.plug.examen.dto.requests.CompraRequest;
import ar.com.plug.examen.enums.EstadosComprasEnum;
import ar.com.plug.examen.enums.EstadosTransaccionEnum;
import java.util.ArrayList;
import java.util.Collections;
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
public class CompraServiceTest {

    @Mock
    private CompraRepository compraRepository;

    @Mock
    private TransaccionRepository transaccionRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private CompraServiceImpl compraService;

    @Test
    public void testListCompras() {
        Mockito.when(compraRepository.findAll()).thenReturn(compraListMock());
        ResponseEntity expected = compraService.listCompras();
        Assert.assertEquals(expected, ResponseEntity.ok(compraListMock()));
    }

    @Test
    public void testAddCompra() {
        Mockito.when(transaccionRepository.findById(transaccionMock().getId())).thenReturn(Optional.of(transaccionMock()));
        Mockito.when(productoRepository.findById(productoMock().getId()))
                .thenReturn(Optional.of(productoMock()));
        ResponseEntity expected = compraService.addCompra(compraRequestMock());
        Assert.assertEquals(expected, ResponseEntity.ok().build());
    }

    @Test
    public void testUpdateCompra() {
        Mockito.when(transaccionRepository.findById(transaccionMock().getId())).thenReturn(Optional.of(transaccionMock()));
        Mockito.when(productoRepository.findById(productoMock().getId()))
                .thenReturn(Optional.of(productoMock()));
        Mockito.when(compraRepository.findByProductoIdAndTransaccion(productoMock().getId(), transaccionMock().getId())).thenReturn(
                Collections.singletonList(compraMock()));
        Mockito.when(compraRepository.save(compraMock())).thenReturn(compraMock());
        ResponseEntity expected = compraService.updateCompra(compraRequestMock());
        Assert.assertEquals(expected, ResponseEntity.ok(compraMock()));
    }

    @Test
    public void testDeleteCompra() {
        Mockito.when(compraRepository.findByProductoIdAndTransaccion(productoMock().getId(), transaccionMock().getId())).thenReturn(compraListMock());
        ResponseEntity expected = compraService.deleteCompraTransaccion(productoMock().getId(), transaccionMock().getId());
        Assert.assertEquals(expected, ResponseEntity.ok().build());
    }

    private Compra compraMock() {
        Compra compra = new Compra();
        compra.setId(1L);
        compra.setProducto(productoMock());
        compra.setEstado(EstadosComprasEnum.PENDIENTE.name());
        compra.setCantidad(1);
        compra.setTransaccion(transaccionMock());
        return compra;
    }

    private List<Compra> compraListMock() {
        List<Compra> compras = new ArrayList<>();
        Compra compra1 = new Compra();
        compra1.setProducto(productoMock());
        compra1.setEstado(EstadosComprasEnum.APROBADO.name());
        compra1.setCantidad(1);
        compra1.setTransaccion(transaccionMock());
        compras.add(compra1);
        Compra compra2 = new Compra();
        compra2.setProducto(productoMock());
        compra2.setEstado(EstadosComprasEnum.APROBADO.name());
        compra2.setCantidad(2);
        compra2.setTransaccion(transaccionMock());
        return compras;
    }

    private CompraRequest compraRequestMock() {
        CompraRequest compraRequest = new CompraRequest();
        compraRequest.setTransaccionId(1L);
        compraRequest.setProductoId(1L);
        compraRequest.setCantidad(1);
        return compraRequest;
    }

    private Producto productoMock() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test");
        producto.setDescripcion("Producto de prueba");
        producto.setPrecio(1L);
        producto.setSku("00000");
        return producto;
    }

    private Transaccion transaccionMock() {
        Transaccion transaccion = new Transaccion();
        transaccion.setId(1L);
        transaccion.setCliente(clienteMock());
        transaccion.setEstado(EstadosTransaccionEnum.FINALIZADO.name());
        transaccion.setTotalTransaccion(1000L);
        return transaccion;
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
}
