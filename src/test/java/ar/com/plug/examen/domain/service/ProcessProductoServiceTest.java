package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Producto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProcessProductoServiceTest {

    @Autowired
    private ProcessProductoService processProductoService;

    @Test
    public void testProcessMessage() {
        Producto producto = new Producto();
        producto.setNombre("unit test");
        producto.setPrecio(1000.0);
        Producto productoResponse = processProductoService.save(producto);

        assertNotNull(productoResponse);
    }
}
