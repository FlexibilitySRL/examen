package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.*;
import ar.com.plug.examen.domain.model.*;
import ar.com.plug.examen.domain.repository.IClienteRepo;
import ar.com.plug.examen.domain.repository.IProductoRepo;
import ar.com.plug.examen.domain.repository.ITransaccionRepo;
import ar.com.plug.examen.domain.repository.IVendedorRepo;
import ar.com.plug.examen.domain.service.ITransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionServiceImpl implements ITransaccionService {

    @Autowired
    private ITransaccionRepo transaccionRepo;
    @Autowired
    private IClienteRepo clienteRepo;
    @Autowired
    private IVendedorRepo vendedorRepo;
    @Autowired
    private IProductoRepo productoRepo;


    /**
     * <h1>agregarTransaccion</h1>
     * <p>Metodo para crear una nueva transaccion</p>
     * <p>Una transaccion se crea como pendiente y sin vendedor asignado</p>
     * @param transaccion - Transaccion que se quiere agregar
     * @return La transaccion recien creada
     * @throws ClienteNoEncontradoException En caso de no encontrar al cliente
     * @throws ProductoNoEncontradoException En caso de no encontrar el producto
     */
    @Override
    public TransaccionDAO agregarTransaccion(TransaccionDAO transaccion) throws ClienteNoEncontradoException, ProductoNoEncontradoException {
        TransaccionDAO newTransaccion = new TransaccionDAO();
        ClienteDAO cliente = new ClienteDAO();
        ProductoDAO producto = new ProductoDAO();
        Optional<ProductoDAO> opProducto = null;
        List<ProductoDAO> listaProductos = new ArrayList<>();
        float precioTotal = 0f;
        Date fechaCreacion = new Date();

        Optional<ClienteDAO> opCliente = clienteRepo.findById(transaccion.getCliente().getIdCliente());
        if (opCliente.isPresent())
            cliente = opCliente.get();
        else
            throw new ClienteNoEncontradoException("El cliente " + transaccion.getCliente().getIdCliente() + " no se encontro en la base de datos");

        for (ProductoDAO prod : transaccion.getListaProductos()) {
            opProducto = productoRepo.findById(prod.getIdProducto());
            if (opProducto.isPresent())
                producto = opProducto.get();
            else
                throw new ProductoNoEncontradoException("El producto " + prod.getIdProducto() + " no se encontro en la base de datos");

            listaProductos.add(producto);
            precioTotal += producto.getPrecio();
        }

        newTransaccion.setEstadoTransaccion(EstadoTransaccion.PENDIENTE);
        newTransaccion.setCliente(cliente);
        newTransaccion.setVendedor(null);
        newTransaccion.setListaProductos(listaProductos);
        newTransaccion.setFechaCreacion(fechaCreacion);
        newTransaccion.setPrecioTotal(precioTotal);

        return transaccionRepo.save(newTransaccion);
    }

    /**
     * <h1>modificarTransaccion</h1>
     * <p>Metodo para modificar el cliente o el vendedor de una transaccion</p>
     * @param newTransaccion - Transaccion con los datos actualizados
     * @return Transaccion modificada
     * @throws TransaccionNoEncontradaException En caso de no encontrar la transaccion
     * @throws ClienteNoEncontradoException En caso de no encontrar al cliente
     */
    @Override
    public TransaccionDAO modificarTransaccion(TransaccionDAO newTransaccion) throws TransaccionNoEncontradaException, ClienteNoEncontradoException, VendedorNoEncontradoException {
        TransaccionDAO oldTransaccion = transaccionRepo.findById(newTransaccion.getIdTransaccion()).orElseThrow(()-> new TransaccionNoEncontradaException("La transaccion " + newTransaccion.getIdTransaccion() + " no se encontro en la base de datos"));
        ClienteDAO cliente = clienteRepo.findById(newTransaccion.getCliente().getIdCliente()).orElseThrow(() -> new  ClienteNoEncontradoException("El cliente " + newTransaccion.getCliente().getIdCliente() + " no se encontro en la base de datos"));
        VendedorDAO vendedor = vendedorRepo.findById(newTransaccion.getVendedor().getIdVendedor()).orElseThrow(() -> new VendedorNoEncontradoException("El vendedor " + newTransaccion.getVendedor().getIdVendedor() + " no se encontro en la base de datos"));
        oldTransaccion.setCliente(cliente);
        oldTransaccion.setVendedor(vendedor);
        return transaccionRepo.save(oldTransaccion);
    }

    /**
     * <h1>listarTransacciones</h1>
     * <p>Metdo para listar todas las transacciones</p>
     * @return Lista de todas las transacciones
     */
    @Override
    public List<TransaccionDAO> listarTransacciones() {
        return transaccionRepo.findAll();
    }

    /**
     *<h1>listarTransacciones</h1>
     * <p>Metodo para listar las transacciones en un estado en particular</p>
     * @param estado - Estado de las transacciones que se quiere mostrar
     * @return Lista de las transacciones filtradas por estado
     */
    @Override
    public List<TransaccionDAO> listarTransacciones(EstadoTransaccion estado) {
        return transaccionRepo.findByEstadoTransaccion(estado);
    }

    /**
     * <h1>buscarTransaccion</h1>
     * <p>Metodo para buscar una transaccion por ID</p>
     * @param id - ID de la transaccion
     * @return La transaccion buscada
     * @throws TransaccionNoEncontradaException En caso de no encontrar la transaccion
     */
    @Override
    public TransaccionDAO buscarTransaccion(Long id) throws TransaccionNoEncontradaException {
        return transaccionRepo.findById(id).orElseThrow(()-> new TransaccionNoEncontradaException("La transaccion " + id + " no se encontro en la base de datos"));
    }

    /**
     * <h1>agregarProductos</h1>
     * <p>Metodo para agregar productos a una transaccion</p>
     * <p>Los productos podran ser agregados solamente a una transaccion pendiente</p>
     * @param idTransaccion - ID de la transaccion a la que se le van a agrear los productos
     * @param listProd - lista de los productos a agregar
     * @return La transaccion con los nuevos productos
     * @throws TransaccionNoEncontradaException En caso de no encontrar la transaccion
     * @throws ProductoNoEncontradoException En caso de no encontrar alguno de los productos
     * @throws OperacionInvalidaException En caso de querer agregar productos a una transaccion que ya fue aprobada o rechazada
     */
    @Override
    public TransaccionDAO agregarProductos(Long idTransaccion, List<ProductoDAO> listProd) throws TransaccionNoEncontradaException, ProductoNoEncontradoException, OperacionInvalidaException {
        TransaccionDAO transaccion = transaccionRepo.findById(idTransaccion).orElseThrow(()-> new TransaccionNoEncontradaException("La transaccion " + idTransaccion + " no se encontro en la base de datos"));
        ProductoDAO producto = null;
        List<ProductoDAO> listaProductos = transaccion.getListaProductos();

        if (transaccion.getEstadoTransaccion() == EstadoTransaccion.PENDIENTE) {
            for (ProductoDAO prod : listProd) {
                producto = productoRepo.findById(prod.getIdProducto()).orElseThrow(() -> new ProductoNoEncontradoException("El producto " + prod.getIdProducto() + " no se encontro en la base de datos"));
                listaProductos.add(producto);
            }
        }
        else {
            throw new OperacionInvalidaException("Esta operacion es invalida. No se pueden agregar nuevos productos a una transaccion ya aprobada o rechazada");
        }

        transaccion.setListaProductos(listaProductos);
        transaccion.setPrecioTotal(calcularPrecioTotal(listaProductos));
        return transaccionRepo.save(transaccion);
    }

    /**
     * <h1>removerProductos</h1>
     * <p>Metodo para remover productos de una transaccion</p>
     * <p>Los productos podran ser removidos solamente de una transaccion pendiente</p>
     * @param idTransaccion - ID de la transaccion a la que se le van a remover los productos
     * @param listProd - Lista de productos a remover
     * @return Transaccion actualizada
     * @throws TransaccionNoEncontradaException En caso de no encontrar la transaccion
     * @throws OperacionInvalidaException En caso de querer remover productos de una transaccion que ya fue aprobada o rechazada
     */
    @Override
    public TransaccionDAO removerProductos(Long idTransaccion, List<ProductoDAO> listProd) throws TransaccionNoEncontradaException, OperacionInvalidaException {
        TransaccionDAO transaccion = transaccionRepo.findById(idTransaccion).orElseThrow(()-> new TransaccionNoEncontradaException("La transaccion " + idTransaccion + " no se encontro en la base de datos"));
        List<ProductoDAO> listaProductosTx = transaccion.getListaProductos();
        boolean encontre = false;
        int i = 0;

        if (transaccion.getEstadoTransaccion() == EstadoTransaccion.PENDIENTE) {
            for (ProductoDAO prod : listProd) {
                i = 0;
                encontre = false;
                while (i < listaProductosTx.size() && !encontre) {
                    if (listaProductosTx.get(i).getIdProducto() == prod.getIdProducto()) {
                        encontre = true;
                        listaProductosTx.remove(i);
                    }
                    else {
                        i++;
                    }
                }
            }
        }
        else {
            throw new OperacionInvalidaException("Esta operacion es invalida. No se pueden remover productos de una transaccion ya aprobada o rechazada");
        }

        transaccion.setListaProductos(listaProductosTx);
        transaccion.setPrecioTotal(calcularPrecioTotal(listaProductosTx));
        return transaccionRepo.save(transaccion);
    }

    private float calcularPrecioTotal(List<ProductoDAO> listaProductos) {
        float precioTotal = 0f;
        for (ProductoDAO producto : listaProductos) {
            precioTotal += producto.getPrecio();
        }
        return precioTotal;
    }
}
