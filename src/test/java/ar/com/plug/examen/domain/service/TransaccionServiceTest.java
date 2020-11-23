package ar.com.plug.examen.domain.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.app.api.ClienteBean;
import ar.com.plug.examen.app.api.ProductoBean;
import ar.com.plug.examen.app.api.TransaccionBean;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.service.impl.TransaccionServiceImpl;
import ar.com.plug.examen.util.StateEnum;

@RunWith(MockitoJUnitRunner.class)
public class TransaccionServiceTest {
	
	@InjectMocks
	private TransaccionServiceImpl impl;

	@Mock
	private TransaccionService service;

	@Test
	public void getTransaccionByIdTest() {
		Producto producto = new Producto();
		producto.setCodProducto("codigo");
		producto.setDescripcionProducto("descripcion");
		producto.setFechaCreacion(LocalDateTime.now());
		producto.setIdProducto(1l);
		producto.setMontoProducto(new BigDecimal(100));
		producto.setNombreProducto("nombre");
		
		Cliente cliente = new Cliente();
		cliente.setApellidoCliente("apellido");
		cliente.setNombreCliente("nombre");
		cliente.setNumeroDocumento("123");
		cliente.setTipoDocumento("CE");
		cliente.setFechaCreacion(LocalDateTime.now());
		cliente.setIdCliente(1l);
		
		Transaccion transaccion = new Transaccion();
		transaccion.setCodigoTrx("codigo");
		transaccion.setEstado(StateEnum.APROBADO);
		transaccion.setFechaCreacion(LocalDateTime.now());
		transaccion.setId(1l);
		transaccion.setIdProducto(producto);
		transaccion.setIdCliente(cliente);
		
		when(service.findByCodigoTrx(any())).thenReturn(transaccion);
		
		TransaccionBean bean = impl.getTransaccionById("codigo");
		
		assertNotNull(bean);
		
	}
	
	@Test
	public void crearTransaccionTest() {
		Transaccion transaccion = new Transaccion();
		TransaccionBean bean = new TransaccionBean();
		
		Producto producto = new Producto();
		producto.setIdProducto(1l);
		ProductoBean productoBean = new ProductoBean();
		producto.setIdProducto(1l);
		
		Cliente cliente = new Cliente();
		cliente.setIdCliente(1l);
		ClienteBean clienteBean = new ClienteBean();
		clienteBean.setIdCliente(1l);
		
		transaccion.setCodigoTrx("codigo");
		transaccion.setEstado(StateEnum.APROBADO);
		transaccion.setFechaCreacion(LocalDateTime.now());
		transaccion.setIdProducto(producto);
		transaccion.setIdCliente(cliente);
		
		bean.setCodigoTrx("codigo");
		bean.setEstado(StateEnum.APROBADO);
		bean.setFechaCreacion(LocalDateTime.now());
		bean.setIdProducto(productoBean);
		bean.setIdCliente(clienteBean);
		
		when(service.save(any())).thenReturn(transaccion);
		
		TransaccionBean bean2 = impl.crearTransaccion(bean);
		assertNotNull(bean2);
	}
}
