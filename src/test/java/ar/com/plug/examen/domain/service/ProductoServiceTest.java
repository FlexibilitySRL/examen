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

import ar.com.plug.examen.app.api.ProductoBean;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.service.impl.ProductoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductoServiceTest {
	
	@InjectMocks
	private ProductoServiceImpl productoServiceImpl;
	
	@Mock
	private ProductoService service;
	
	@Test
	public void getProductoByIDTest() {
		Producto producto = new Producto();
		producto.setCodProducto("codigo");
		producto.setDescripcionProducto("descripcion");
		producto.setFechaCreacion(LocalDateTime.now());
		producto.setIdProducto(1l);
		producto.setMontoProducto(new BigDecimal(100));
		producto.setNombreProducto("nombre");
		
		when(service.existsById(any())).thenReturn(Boolean.TRUE);
		when(service.getOne(any())).thenReturn(producto);
		
		ProductoBean bean = productoServiceImpl.getProductoByID(1l);
		
		assertNotNull(bean);
	}
	
	@Test
	public void createProductoTest() {
		ProductoBean bean = new ProductoBean();
		Producto producto = new Producto();
		
		bean.setCodProducto("codigo");
		bean.setDescripcionProducto("descripcion");
		bean.setFechaCreacion(LocalDateTime.now());
		bean.setMontoProducto(new BigDecimal(100));
		bean.setNombreProducto("nombre");
		
		producto.setCodProducto("codigo");
		producto.setDescripcionProducto("descripcion");
		producto.setFechaCreacion(LocalDateTime.now());
		producto.setMontoProducto(new BigDecimal(100));
		producto.setNombreProducto("nombre");
		
		when(service.save(any())).thenReturn(producto);
		
		ProductoBean bean2 = productoServiceImpl.createProducto(bean);
		assertNotNull(bean2);
	}
	
	@Test
	public void deleteProductoTest() {
		ProductoBean bean = new ProductoBean();
		
		bean.setCodProducto("codigo");
		bean.setDescripcionProducto("descripcion");
		bean.setFechaCreacion(LocalDateTime.now());
		bean.setMontoProducto(new BigDecimal(100));
		bean.setNombreProducto("nombre");
		bean.setIdProducto(1l);
		
		productoServiceImpl.deleteProducto(bean);
		
		assertNotNull(bean);
	}

}
