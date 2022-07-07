package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ClienteApi;
import ar.com.plug.examen.domain.model.Cliente;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProcessClienteRestTest {

    @Autowired
    ClienteController clienteController;

    @Test
    public void testProcessMessage() {
        ClienteApi clienteApi = new ClienteApi();
        clienteApi.setNombre("Javier");
        clienteApi.setApellido("Florentín");

        ResponseEntity<?> responseEntityPost = clienteController.postCliente(clienteApi);

        assert responseEntityPost.getStatusCode().value() == 200;
        assert responseEntityPost.getBody() != null;

        Gson gson = new Gson();

        Cliente clienteResponse = gson.fromJson(responseEntityPost.getBody().toString(), Cliente.class);
        assert clienteResponse != null;

        clienteApi.setNombre("Javier Editado");
        clienteApi.setApellido("Florentín Editado");

        ResponseEntity<?> responseEntityPut = clienteController.putCliente(clienteResponse.getId(), clienteApi);

        assert responseEntityPut.getStatusCode().value() == 200;

        ResponseEntity<?> responseEntityGet = clienteController.getCliente(clienteResponse.getId());

        assert responseEntityGet.getStatusCode().value() == 200;

        ResponseEntity<?> responseEntityGetList = clienteController.getClientes();

        assert responseEntityGetList.getStatusCode().value() == 200;

        ResponseEntity<?> responseEntityDelete = clienteController.deleteCliente(clienteResponse.getId());

        assert responseEntityDelete.getStatusCode().value() == 200;
    }
}
