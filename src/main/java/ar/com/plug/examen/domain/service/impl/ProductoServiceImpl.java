package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.ProductoNoEncontradoException;
import ar.com.plug.examen.domain.model.EstadoLogico;
import ar.com.plug.examen.domain.model.ProductoDAO;
import ar.com.plug.examen.domain.repository.IProductoRepo;
import ar.com.plug.examen.domain.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepo productoRepo;

    /**
     * <h1>agregarProducto</h1>
     * <p>Metodo para agregar un nuevo producto</p>
     * @param producto - Producto que se quiere agregar
     * @return El producto creado
     */
    @Override
    public ProductoDAO agregarProducto(ProductoDAO producto) {
        producto.setEstadoLogico(EstadoLogico.ACTIVO);
        return productoRepo.save(producto);
    }

    /**
     * <h1>borrarProducto</h1>
     * <p>Metodo para realizar un borrado logico de un producto</p>
     * @param id - ID del producto
     * @return Producto eliminado
     * @throws ProductoNoEncontradoException En caso de no encontrar el producto
     */
    @Override
    public ProductoDAO borrarProducto(Long id) throws ProductoNoEncontradoException {
        ProductoDAO producto = productoRepo.findById(id).orElseThrow(() -> new ProductoNoEncontradoException("El producto " + id + " no se encontro en la base de datos"));
        producto.setEstadoLogico(EstadoLogico.ELIMINADO);
        return productoRepo.save(producto);
    }

    /**
     * <h1>modificarProducto</h1>
     * <p>Metodo para modificar un producto</p>
     * @param newProducto - Producto que se quiere modificar
     * @return El producto modificado
     * @throws ProductoNoEncontradoException En caso de no encontrar el producto
     */
    @Override
    public ProductoDAO modificarProducto(ProductoDAO newProducto) throws ProductoNoEncontradoException {
        ProductoDAO oldProducto = productoRepo.findById(newProducto.getIdProducto()).orElseThrow(() -> new ProductoNoEncontradoException("El producto " + newProducto.getIdProducto() + " no se encontro en la base de datos"));
        oldProducto.setDescripcion(newProducto.getDescripcion());
        oldProducto.setPrecio(newProducto.getPrecio());
        return productoRepo.save(oldProducto);
    }

    /**
     * <h1>listarProductos</h1>
     * <p>Metodo para listar todos los productos</p>
     * @return Lista de todos los prductos
     */
    @Override
    public List<ProductoDAO> listarProductos() {
        return productoRepo.findAll();
    }

    /**
     * <h1>listarProductosActivos</h1>
     * <p>Metodo para listar los productos activos</p>
     * @return Lista de los productos activos
     */
    @Override
    public List<ProductoDAO> listarProductosActivos() {
        return productoRepo.findByEstadoLogico(EstadoLogico.ACTIVO);
    }

    /**
     * <h1>buscarProducto</h1>
     * <p>Metodo para buscar un producto</p>
     * @param id - ID del producto
     * @return El producto buscado
     * @throws ProductoNoEncontradoException En caso de no encontrar el producto
     */
    @Override
    public ProductoDAO buscarProducto(Long id) throws ProductoNoEncontradoException {
        return productoRepo.findById(id).orElseThrow(() -> new ProductoNoEncontradoException("El producto " + id + " no se encontro en la base de datos"));
    }
}
