package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.repo.IProductoRepo;

@RunWith(SpringRunner.class)
public class ProcessBAMServiceTest {
	@Autowired
	private IProductoRepo _producto;
	
	@Test
	public void altaProducto() {
		Producto producto = new Producto();
		producto.setIdProducto(5);
		producto.setNombre("Teclado");
		producto.setDescripcion("Color negro");
		producto.setPrecio(100);
		
		_producto.save(producto);
		
		assertEquals(producto.getIdProducto(),5);
	}
	
	public void altaCliente(){
		Cliente cliente = new Cliente();
		cliente.setIdCliente(12);
		cliente.setNombre("carlos");
	}	

}
