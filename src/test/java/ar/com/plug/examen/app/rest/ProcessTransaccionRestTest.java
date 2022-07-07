package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ClienteApi;
import ar.com.plug.examen.app.api.ProductoApi;
import ar.com.plug.examen.app.api.TransaccionApi;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Producto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProcessTransaccionRestTest {

    @Autowired
    TransaccionController transaccionController;

    @Autowired
    ProductoController productoController;

    @Autowired
    ClienteController clienteController;

    @Test
    public void testProcessMessage() {
        ProductoApi productoApi = new ProductoApi();
        productoApi.setNombre("Producto");
        productoApi.setPrecio(1000.0);

        ResponseEntity<?> responseProductoEntityPost = productoController.postProducto(productoApi);

        assert responseProductoEntityPost.getStatusCode().value() == 200;
        assert responseProductoEntityPost.getBody() != null;

        Gson gson = new Gson();

        Producto responseProducto = gson.fromJson(responseProductoEntityPost.getBody().toString(), Producto.class);
        assert responseProducto != null;

        TransaccionApi api = new TransaccionApi();
        List<Long> productos = new ArrayList<>();
        productos.add(responseProducto.getId());
        api.setProductos(productos);

        ClienteApi clienteApi = new ClienteApi();
        clienteApi.setNombre("Javier");
        clienteApi.setApellido("Florentín");

        ResponseEntity<?> responseClienteEntityPost = clienteController.postCliente(clienteApi);

        assert responseClienteEntityPost.getStatusCode().value() == 200;
        assert responseClienteEntityPost.getBody() != null;

        Cliente clienteResponse = gson.fromJson(responseClienteEntityPost.getBody().toString(), Cliente.class);
        assert clienteResponse != null;

        api.setCliente_id(clienteResponse.getId());

        ResponseEntity<?> responseEntityPost = transaccionController.postTransaccion(api);

        assert responseEntityPost.getStatusCode().value() == 200;
        assert responseEntityPost.getBody() != null;

        Producto response = gson.fromJson(responseEntityPost.getBody().toString(), Producto.class);
        assert response != null;

        ResponseEntity<?> responseEntityPut = transaccionController.patchTransaccionStatus(response.getId(), "APROBADO");

        assert responseEntityPut.getStatusCode().value() == 200;

        ResponseEntity<?> responseEntityGet = transaccionController.getTransaccion(response.getId());

        assert responseEntityGet.getStatusCode().value() == 200;

        ResponseEntity<?> responseEntityGetList = transaccionController.getTransacciones();

        assert responseEntityGetList.getStatusCode().value() == 200;
    }
}
