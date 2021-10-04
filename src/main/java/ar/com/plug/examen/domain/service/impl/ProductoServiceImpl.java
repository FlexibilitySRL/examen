package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.repository.ProductoRepository;
import ar.com.plug.examen.domain.service.ProductoService;
import ar.com.plug.examen.dto.requests.ProductoRequest;
import ar.com.plug.examen.dto.responses.ProductoResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoResponse> findAllProductos(){

        ProductoResponse productoResponse = new ProductoResponse();
        List<Producto> productos = productoRepository.findAll();
        List<ProductoResponse> productoResponseList =
                productos.stream().map(producto -> productoResponse.builder()
                        .id(producto.getId())
                        .nombre(producto.getNombre())
                        .descripcion(producto.getDescripcion())
                        .precio(producto.getPrecio())
                        .sku(producto.getSku())
                        .build()).collect(
                        Collectors.toList());

        return productoResponseList;
    }

    @Override
    public ResponseEntity addProducto(ProductoRequest productoRequest){
        Producto producto = new Producto();
        producto.setNombre(productoRequest.getNombre());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setSku(productoRequest.getSku());
        return ResponseEntity.ok(productoRepository.save(producto));
    }

    @Override
    public Producto findById(Long id){
        Producto producto = productoRepository.findById(id).orElse(null);
    if (Objects.nonNull(producto)) {
        return producto;
    }
    else{
        throw new RuntimeException("No se encuentra el producto con identificador " + id);
    }
    }
    @Override
    public ResponseEntity updateProducto(ProductoRequest productoRequest, Long id) {

        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isPresent()) {
            producto.get().setNombre(productoRequest.getNombre());
            producto.get().setDescripcion(productoRequest.getDescripcion());
            producto.get().setPrecio(productoRequest.getPrecio());
            producto.get().setSku(productoRequest.getSku());
            return ResponseEntity.ok(productoRepository.save(producto.get()));
        }
        else {
            throw new RuntimeException("No se encontró un producto con el identificador " + id + ".");
        }
    }

    @Override
    public ResponseEntity<Producto> deleteProducto(Long id){
        Producto producto = productoRepository.findById(id).orElse(null);
        if(Objects.nonNull(producto)) {
            productoRepository.delete(producto);
            return ResponseEntity.ok().build();
        }
        else {
            throw new RuntimeException("No se encontró un producto con el identificador " + id + ".");
        }
    }
}
