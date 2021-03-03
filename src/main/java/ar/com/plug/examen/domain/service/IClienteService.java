package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exception.ClienteNoEncontradoException;
import ar.com.plug.examen.domain.model.ClienteDAO;
import ar.com.plug.examen.domain.model.TransaccionDAO;

import java.util.List;

public interface IClienteService {

    public abstract ClienteDAO agregarCliente(ClienteDAO cliente);
    public abstract ClienteDAO borrarCliente(Long id) throws ClienteNoEncontradoException;
    public abstract ClienteDAO modificarCliente(ClienteDAO cliente) throws ClienteNoEncontradoException;
    public abstract List<ClienteDAO> listarClientes();
    public abstract List<ClienteDAO> listarClientesActivos();
    public abstract ClienteDAO buscarCliente(Long id) throws ClienteNoEncontradoException;
    public abstract List<TransaccionDAO> buscarTransaccionesPorCliente(Long id) throws ClienteNoEncontradoException;
}
