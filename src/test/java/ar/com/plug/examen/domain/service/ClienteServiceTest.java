package ar.com.plug.examen.domain.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.app.api.ClienteBean;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.service.impl.ClienteServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

	@InjectMocks
	private ClienteServiceImpl clienteServiceImpl;
	
	@Mock
	private ClienteService service;
	
	@Test
	public void getClienteByIDTest() {
		Cliente cliente = new Cliente();
		cliente.setApellidoCliente("apellido");
		cliente.setNombreCliente("nombre");
		cliente.setNumeroDocumento("123");
		cliente.setTipoDocumento("CE");
		cliente.setFechaCreacion(LocalDateTime.now());
		cliente.setIdCliente(1l);
		
		when(service.existsById(any())).thenReturn(Boolean.TRUE);
		when(service.getOne(any())).thenReturn(cliente);
		
		ClienteBean cliente2 = clienteServiceImpl.getClienteByID(1l);
		
		assertNotNull(cliente2);
	}
	
	@Test
	public void createClienteTest() {
		ClienteBean bean = new ClienteBean();
		Cliente cliente = new Cliente();
		
		bean.setApellidoCliente("apellido");
		bean.setNombreCliente("nombre");
		bean.setNumeroDocumento("123");
		bean.setTipoDocumento("CE");
		bean.setFechaCreacion(LocalDateTime.now());
		
		cliente.setApellidoCliente(bean.getApellidoCliente());
		cliente.setFechaCreacion(bean.getFechaCreacion());
		cliente.setNombreCliente(bean.getNombreCliente());
		cliente.setNumeroDocumento(bean.getNumeroDocumento());
		cliente.setTipoDocumento(bean.getTipoDocumento());
		
		when(service.save(any())).thenReturn(cliente);
		ClienteBean cliente2 = clienteServiceImpl.createCliente(bean);
		
		assertNotNull(cliente2);
	}
	
	@Test
	public void eliminarClienteTest() {
		ClienteBean bean = new ClienteBean();
		
		bean.setApellidoCliente("apellido");
		bean.setNombreCliente("nombre");
		bean.setNumeroDocumento("123");
		bean.setTipoDocumento("CE");
		bean.setFechaCreacion(LocalDateTime.now());
		bean.setIdCliente(1l);
		
		clienteServiceImpl.eliminarCliente(bean);
		
		assertNotNull(bean);
	}
	
}
