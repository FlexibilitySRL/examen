package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exception.ProductoNoEncontradoException;
import ar.com.plug.examen.domain.model.ProductoDAO;

import java.util.List;

public interface IProductoService {

    public abstract ProductoDAO agregarProducto(ProductoDAO producto);
    public abstract ProductoDAO borrarProducto(Long id) throws ProductoNoEncontradoException;
    public abstract ProductoDAO modificarProducto(ProductoDAO producto) throws ProductoNoEncontradoException;
    public abstract List<ProductoDAO> listarProductos();
    public abstract List<ProductoDAO> listarProductosActivos();
    public abstract ProductoDAO buscarProducto(Long id) throws ProductoNoEncontradoException;
}
