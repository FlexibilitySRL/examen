package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.exception.MicroserviceErrorException;

public interface ProductoService {

    Producto findProductoById(Integer id);

    Producto saveProducto(Producto producto) throws MicroserviceErrorException;

    Producto updateProducto(Producto producto, Integer id) throws MicroserviceErrorException;

    void deleteProductoById(Integer id) throws MicroserviceErrorException;
}
