package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ProductoApi;
import ar.com.plug.examen.domain.model.Producto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProcessProductoRestTest {

    @Autowired
    ProductoController productoController;

    @Test
    public void testProcessMessage() {
        ProductoApi api = new ProductoApi();
        api.setNombre("Producto");
        api.setPrecio(1000.0);

        ResponseEntity<?> responseEntityPost = productoController.postProducto(api);

        assert responseEntityPost.getStatusCode().value() == 200;
        assert responseEntityPost.getBody() != null;

        Gson gson = new Gson();

        Producto response = gson.fromJson(responseEntityPost.getBody().toString(), Producto.class);
        assert response != null;

        api.setNombre("Producto Editado");

        ResponseEntity<?> responseEntityPut = productoController.patchProducto(response.getId(), api);

        assert responseEntityPut.getStatusCode().value() == 200;

        ResponseEntity<?> responseEntityGet = productoController.getProducto(response.getId());

        assert responseEntityGet.getStatusCode().value() == 200;

        ResponseEntity<?> responseEntityGetList = productoController.getProductos();

        assert responseEntityGetList.getStatusCode().value() == 200;

        ResponseEntity<?> responseEntityDelete = productoController.deleteProducto(response.getId());

        assert responseEntityDelete.getStatusCode().value() == 200;
    }
}
