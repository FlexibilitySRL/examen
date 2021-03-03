package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exception.*;
import ar.com.plug.examen.domain.model.EstadoTransaccion;
import ar.com.plug.examen.domain.model.ProductoDAO;
import ar.com.plug.examen.domain.model.TransaccionDAO;

import java.util.List;

public interface ITransaccionService {

    public abstract TransaccionDAO agregarTransaccion(TransaccionDAO transaccion) throws ClienteNoEncontradoException, ProductoNoEncontradoException;
    public abstract TransaccionDAO modificarTransaccion(TransaccionDAO transaccion) throws TransaccionNoEncontradaException, ClienteNoEncontradoException, VendedorNoEncontradoException;
    public abstract List<TransaccionDAO> listarTransacciones();
    public abstract List<TransaccionDAO> listarTransacciones(EstadoTransaccion estado);
    public abstract TransaccionDAO buscarTransaccion(Long id) throws TransaccionNoEncontradaException;
    public abstract TransaccionDAO agregarProductos(Long idTransaccion, List<ProductoDAO> listaProductos) throws TransaccionNoEncontradaException, ProductoNoEncontradoException, OperacionInvalidaException;
    public abstract TransaccionDAO removerProductos(Long idTransaccion, List<ProductoDAO> listaProductos) throws TransaccionNoEncontradaException, OperacionInvalidaException;
}
