package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.ClienteNoEncontradoException;
import ar.com.plug.examen.domain.model.ClienteDAO;
import ar.com.plug.examen.domain.model.EstadoLogico;
import ar.com.plug.examen.domain.model.TransaccionDAO;
import ar.com.plug.examen.domain.repository.IClienteRepo;
import ar.com.plug.examen.domain.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepo clienteRepo;

    /**
     * <h1>agregarCliente</h1>
     * <p>Metodo para agregar un nuevo cliente</p>
     * @param cliente - {@link ClienteDAO} cliente a agregar
     * @return Cliente recien creado
     */
    @Override
    public ClienteDAO agregarCliente(ClienteDAO cliente) {
        cliente.setEstadoLogico(EstadoLogico.ACTIVO);
        cliente.setListaTransacciones(new ArrayList<>());
        return clienteRepo.save(cliente);
    }

    /**
     * <h1>borrarCliente</h1>
     * <p>Metodo para realizar un borrado logico de un cliente</p>
     * @param id - ID del cliente
     * @throws ClienteNoEncontradoException En caso de no encontrar al cliente
     * @return Cliente eliminado (@see ClienteDAO)
     */
    @Override
    public ClienteDAO borrarCliente(Long id) throws ClienteNoEncontradoException {
        ClienteDAO cliente = clienteRepo.findById(id).orElseThrow((() -> new ClienteNoEncontradoException("El cliente " + id + " no se encontro en la base de datos")));
        cliente.setEstadoLogico(EstadoLogico.ELIMINADO);
        clienteRepo.save(cliente);
        return cliente;
    }

    /**
     * <h1>modificarCliente</h1>
     * <p>Metodo para actualizar los datos de un cliente</p>
     * @param newCliente - {@link ClienteDAO} Datos del cliente a actualizar
     * @return {@link ClienteDAO} - El cliente con los datos actualizados
     * @throws ClienteNoEncontradoException En caso de no encontrar al cliente
     */
    public ClienteDAO modificarCliente(ClienteDAO newCliente) throws ClienteNoEncontradoException {
        ClienteDAO oldCliente = clienteRepo.findById(newCliente.getIdCliente()).orElseThrow(() -> new  ClienteNoEncontradoException("El cliente " + newCliente.getIdCliente() + " no se encontro en la base de datos"));
        oldCliente.setNombre(newCliente.getNombre());
        oldCliente.setApellido(newCliente.getApellido());
        oldCliente.setDni(newCliente.getDni());
        oldCliente.setEmail(newCliente.getEmail());
        oldCliente.setTelefono(newCliente.getTelefono());
        return clienteRepo.save(oldCliente);
    }

    /**
     * <h1>listarClientes</h1>
     * <p>Metodo para mostrar todos los clientes</p>
     * @return Lista de todos los clientes
     */
    @Override
    public List<ClienteDAO> listarClientes() {
        return clienteRepo.findAll();
    }

    /**
     * <h1>listarClientesActivos</h1>
     * <p>Metodo para listar solamente a los clientes activos</p>
     * @return Lista de todos los clientes activos
     */
    public List<ClienteDAO> listarClientesActivos() {
        return clienteRepo.findByEstadoLogico(EstadoLogico.ACTIVO);
    }

    /**
     * <h1>buscarCliente</h1>
     * <p>Metodo para buscar un cliente</p>
     * @param id - ID del cliente
     * @return {@link ClienteDAO} Cliente buscado
     * @throws ClienteNoEncontradoException En caso de no encontrar al cliente
     */
    @Override
    public ClienteDAO buscarCliente(Long id) throws ClienteNoEncontradoException {
        return clienteRepo.findById(id).orElseThrow((() -> new ClienteNoEncontradoException("El cliente " + id + " no se encontro en la base de datos")));
    }

    /**
     * <h1>buscarTransaccionesPorCliente</h1>
     * <p>Metodo para listar todas las transacciones de un cliente</p>
     * @param idCliente - ID del cliente
     * @return Lista de las transacciones
     * @throws ClienteNoEncontradoException En caso de no encontrar al cliente
     */
    @Override
    public List<TransaccionDAO> buscarTransaccionesPorCliente(Long idCliente) throws ClienteNoEncontradoException {
        ClienteDAO cliente = clienteRepo.findById(idCliente).orElseThrow((() -> new ClienteNoEncontradoException("El cliente " + idCliente + " no se encontro en la base de datos")));
        return cliente.getListaTransacciones();
    }
}
