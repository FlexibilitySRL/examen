package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.model.Transaccion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProcessTransaccionServiceTest {

    @Autowired
    private ProcessTransaccionService processTransaccionService;

    @Test
    public void testProcessMessage() {
        Transaccion transaccion = new Transaccion();

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Unit");
        cliente.setApellido("Test");
        transaccion.setCliente(cliente);

        List<Producto> productos = new ArrayList<>();
        Producto producto1 = new Producto();
        producto1.setId(1L);
        producto1.setNombre("Producto1");
        producto1.setPrecio(1000.0);
        productos.add(producto1);
        transaccion.setProductos(productos);

        transaccion.setPrecioTotal(1000.0);
        transaccion.setStatus("PRUEBA");

        Transaccion transaccionResponse = processTransaccionService.save(transaccion);

        assertNotNull(transaccionResponse);
    }
}
