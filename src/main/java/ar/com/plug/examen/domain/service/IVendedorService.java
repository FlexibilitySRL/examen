package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exception.OperacionInvalidaException;
import ar.com.plug.examen.domain.exception.TransaccionNoEncontradaException;
import ar.com.plug.examen.domain.exception.VendedorNoEncontradoException;
import ar.com.plug.examen.domain.model.EstadoTransaccion;
import ar.com.plug.examen.domain.model.TransaccionDAO;
import ar.com.plug.examen.domain.model.VendedorDAO;

import java.util.List;

public interface IVendedorService {

    public abstract VendedorDAO agregarVendedor(VendedorDAO vendedor);
    public abstract VendedorDAO borrarVendedor(Long id) throws VendedorNoEncontradoException;
    public abstract VendedorDAO modificarVendedor(VendedorDAO vendedor) throws VendedorNoEncontradoException;
    public abstract List<VendedorDAO> listarVendedores();
    public abstract List<VendedorDAO> listarVendedoresActivos();
    public abstract VendedorDAO buscarVendedor(Long id) throws VendedorNoEncontradoException;
    public abstract List<TransaccionDAO> buscarTransaccionesPorVendedor(Long id) throws VendedorNoEncontradoException;
    public abstract TransaccionDAO aprobarTransaccion(Long idTransaccion, Long idVendedor) throws TransaccionNoEncontradaException, VendedorNoEncontradoException;
    public abstract TransaccionDAO rechazarTransaccion(Long idTransaccion, Long idVendedor) throws TransaccionNoEncontradaException, VendedorNoEncontradoException;
}
