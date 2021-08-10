package ar.com.plug.examen.app.rest;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.DetalleTransaccion;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.model.Vendedor;
import ar.com.plug.examen.domain.service.TransaccionService;
import ar.com.plug.examen.domain.util.EstadoTransaccion;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransaccionControllerTest {

	@MockBean
	private TransaccionService transactionService;

	@Autowired
	private TestRestTemplate restTemplate;
	
	private Transaccion transaccion;
	
	@Before
	public void initialice() {
		Cliente cliente = new Cliente(22145112, "López", "Raul", "Los Alamos 123", parseStringToDate("1991-02-05"), "0343444895");
		Vendedor vendedor = new Vendedor(22145112, "Lares", "Mario", "El Esquel 333", parseStringToDate("1985-02-05"), "0343447984");
		Producto producto = new Producto("Linterna", 250);
		transaccion = new Transaccion(cliente, vendedor, new Date(), EstadoTransaccion.EN_ESPERA.getDescription());
		DetalleTransaccion detalle = new DetalleTransaccion(transaccion, producto, 1);
		transaccion.getDetalles().add(detalle);
	}


	@SuppressWarnings("rawtypes")
	@Test
	public void testFindAllWithOnlyOneElement() throws Exception {
		when(transactionService.getTransactions()).thenReturn(Lists.newArrayList(transaccion));
		ResponseEntity<Set> responseEntity = restTemplate.getForEntity("/transacciones", Set.class);
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		assertTrue(responseEntity.getBody().size() == 1);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testFindAllWithoutElements() throws Exception {
		when(transactionService.getTransactions()).thenReturn(Lists.newArrayList());
		ResponseEntity<Set> responseEntity = restTemplate.getForEntity("/transacciones", Set.class);
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		assertTrue(responseEntity.getBody().size() == 0);
	}

	private Date parseStringToDate(String value) {
		Date result = null;
		try {
			result = new SimpleDateFormat("yyyy-MM-dd").parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}
