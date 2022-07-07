package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.VendedorApi;
import ar.com.plug.examen.domain.model.Vendedor;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProcessVendedorRestTest {

    @Autowired
    VendedorController vendedorController;

    @Test
    public void testProcessMessage() {
        VendedorApi vendedorApi = new VendedorApi();
        vendedorApi.setNombre("Javier");
        vendedorApi.setApellido("Florentín");

        ResponseEntity<?> responseEntityPost = vendedorController.postVendedor(vendedorApi);

        assert responseEntityPost.getStatusCode().value() == 200;
        assert responseEntityPost.getBody() != null;

        Gson gson = new Gson();

        Vendedor response = gson.fromJson(responseEntityPost.getBody().toString(), Vendedor.class);
        assert response != null;

        vendedorApi.setNombre("Javier Editado");
        vendedorApi.setApellido("Florentín Editado");

        ResponseEntity<?> responseEntityPut = vendedorController.put(response.getId(), vendedorApi);

        assert responseEntityPut.getStatusCode().value() == 200;

        ResponseEntity<?> responseEntityGet = vendedorController.get(response.getId());

        assert responseEntityGet.getStatusCode().value() == 200;

        ResponseEntity<?> responseEntityGetList = vendedorController.getVendedores();

        assert responseEntityGetList.getStatusCode().value() == 200;

        ResponseEntity<?> responseEntityDelete = vendedorController.deleteVendedor(response.getId());

        assert responseEntityDelete.getStatusCode().value() == 200;
    }
}
