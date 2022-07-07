package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Cliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProcessClienteServiceTest {

    @Autowired
    private ProcessClienteService processClienteService;

    @Test
    public void testProcessMessage() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Avelino");
        cliente.setApellido("Florentín");
        Cliente clienteResponse = processClienteService.save(cliente);

        assertNotNull(clienteResponse);
    }
}
