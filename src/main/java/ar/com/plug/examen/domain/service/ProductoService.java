package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.dto.requests.ProductoRequest;
import ar.com.plug.examen.dto.responses.ProductoResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ProductoService {
    List<ProductoResponse> findAllProductos();
    ResponseEntity<Producto> addProducto(ProductoRequest productoRequest);
    Producto findById(Long id);
    ResponseEntity<Producto> updateProducto(ProductoRequest productoRequest, Long id);
    ResponseEntity<Producto> deleteProducto(Long id);
}
