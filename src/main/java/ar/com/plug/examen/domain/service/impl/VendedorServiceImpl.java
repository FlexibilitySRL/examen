package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.OperacionInvalidaException;
import ar.com.plug.examen.domain.exception.TransaccionNoEncontradaException;
import ar.com.plug.examen.domain.exception.VendedorNoEncontradoException;
import ar.com.plug.examen.domain.model.EstadoLogico;
import ar.com.plug.examen.domain.model.EstadoTransaccion;
import ar.com.plug.examen.domain.model.TransaccionDAO;
import ar.com.plug.examen.domain.model.VendedorDAO;
import ar.com.plug.examen.domain.repository.ITransaccionRepo;
import ar.com.plug.examen.domain.repository.IVendedorRepo;
import ar.com.plug.examen.domain.service.IVendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VendedorServiceImpl implements IVendedorService {

    @Autowired
    private IVendedorRepo vendedorRepo;
    @Autowired
    private ITransaccionRepo transaccionRepo;


    /**
     * <h1>agregarVendedor</h1>
     * <p>Metodo para agregar un nuevo vendedor</p>
     * @param vendedor - Vendedor a agregar
     * @return Vendedor recien creado
     */
    @Override
    public VendedorDAO agregarVendedor(VendedorDAO vendedor) {
        vendedor.setEstadoLogico(EstadoLogico.ACTIVO);
        vendedor.setListaTransacciones(new ArrayList<>());
        return vendedorRepo.save(vendedor);
    }

    /**
     * <h1>borrarVendedor</h1>
     * <p>Metodo para realizar un borrado logico de un vendedor</p>
     * @param id - ID del vendedor
     * @throws VendedorNoEncontradoException En caso de no encontrar al vendedor
     * @return Vendedor eliminado
     */
    @Override
    public VendedorDAO borrarVendedor(Long id) throws VendedorNoEncontradoException {
        VendedorDAO vendedor = vendedorRepo.findById(id).orElseThrow((() -> new VendedorNoEncontradoException("El vendedor " + id + " no se encontro en la base de datos")));
        vendedor.setEstadoLogico(EstadoLogico.ELIMINADO);
        vendedorRepo.save(vendedor);
        return vendedor;
    }

    /**
     * <h1>modificarVendedor</h1>
     * <p>Metodo para actualizar los datos del vendedor</p>
     * @param newVendedor - Vendedor a actualizar
     * @return El vendedor con los datos actualizados
     * @throws VendedorNoEncontradoException En caso de no encontrar al vendedor
     */
    public VendedorDAO modificarVendedor (VendedorDAO newVendedor) throws VendedorNoEncontradoException {
        VendedorDAO oldVendedor = vendedorRepo.findById(newVendedor.getIdVendedor()).orElseThrow(() -> new VendedorNoEncontradoException("El vendedor " + newVendedor.getIdVendedor() + " no se encontro en la base de datos"));
        oldVendedor.setNombre(newVendedor.getNombre());
        oldVendedor.setApellido(newVendedor.getApellido());
        return vendedorRepo.save(oldVendedor);
    }

    /**
     * <h1>listarVendedores</h1>
     * <p>Metodo para mostrar todos los vendedores</p>
     * @return Lista de todos los vendedores
     */
    @Override
    public List<VendedorDAO> listarVendedores() {
        return vendedorRepo.findAll();
    }

    /**
     * <h1>listarVendedoresActivos</h1>
     * <p>Metodo para listar solamente a los vendedores activos</p>
     * @return Lista de todos los vendedores activos
     */
    @Override
    public List<VendedorDAO> listarVendedoresActivos() {
        return vendedorRepo.findByEstadoLogico(EstadoLogico.ACTIVO);
    }

    /**
     * <h1>buscarVendedor</h1>
     * <p>Metodo para buscar un vendedor</p>
     * @param id - ID del vendedor
     * @return Vendedor buscado
     * @throws VendedorNoEncontradoException En caso de no encontrar al vendedor
     */
    @Override
    public VendedorDAO buscarVendedor(Long id) throws VendedorNoEncontradoException {
        return vendedorRepo.findById(id).orElseThrow((() -> new VendedorNoEncontradoException("El vendedor " + id + " no se encontro en la base de datos")));
    }

    /**
     * <h1>buscarTransaccionesPorVendedor</h1>
     * <p>Metodo para listar las transacciones de un vendedor</p>
     * @param idVendedor - ID del vendedor
     * @return Listado de transacciones del vendedor
     * @throws VendedorNoEncontradoException En caso de no encontrar al vendedor
     */
    @Override
    public List<TransaccionDAO> buscarTransaccionesPorVendedor(Long idVendedor) throws VendedorNoEncontradoException {
        VendedorDAO vendedor = vendedorRepo.findById(idVendedor).orElseThrow((() -> new VendedorNoEncontradoException("El vendedor " + idVendedor + " no se encontro en la base de datos")));
        return vendedor.getListaTransacciones();
    }

    /**
     * <h1>aprobarTransaccion</h1>
     * <p>Metodo para aprobar una transaccion</p>
     * @param idTransaccion - ID de la transaccion a aprobar
     * @param idVendedor - ID del vendedor que aprueba
     * @return La transaccion aprobada
     * @throws TransaccionNoEncontradaException En caso de no encontrar la transaccion
     * @throws VendedorNoEncontradoException En caso de no encontrar al vendedor
     */
    @Override
    public TransaccionDAO aprobarTransaccion(Long idTransaccion, Long idVendedor) throws TransaccionNoEncontradaException, VendedorNoEncontradoException {
        return aprobarRechazarTransaccion(idTransaccion, idVendedor, EstadoTransaccion.APROBADO);
    }

    /**
     * <h1>rechazarTransaccion</h1>
     * <p>Metodo para rechazar una transaccion</p>
     * @param idTransaccion - ID de la transaccion a rechazar
     * @param idVendedor - ID del vendedor que rechaza
     * @return La transaccion rechazada
     * @throws TransaccionNoEncontradaException En caso de no encontrar la transaccion
     * @throws VendedorNoEncontradoException En caso de no encontrar al vendedor
     */
    @Override
    public TransaccionDAO rechazarTransaccion(Long idTransaccion, Long idVendedor) throws TransaccionNoEncontradaException, VendedorNoEncontradoException {
        return aprobarRechazarTransaccion(idTransaccion, idVendedor, EstadoTransaccion.RECHAZADO);
    }

    private TransaccionDAO aprobarRechazarTransaccion(Long idTransaccion, Long idVendedor, EstadoTransaccion estado) throws TransaccionNoEncontradaException, VendedorNoEncontradoException {
        TransaccionDAO transaccion = transaccionRepo.findById(idTransaccion).orElseThrow(()-> new TransaccionNoEncontradaException("La transaccion " + idTransaccion + " no se encontro en la base de datos"));
        VendedorDAO vendedor = vendedorRepo.findById(idVendedor).orElseThrow(() -> new VendedorNoEncontradoException("El vendedor " + idVendedor + " no se encontro en la base de datos"));
        Date fechaModificacion = new Date();
        transaccion.setVendedor(vendedor);
        transaccion.setEstadoTransaccion(estado);
        transaccion.setFechaModificacion(fechaModificacion);
        return transaccionRepo.save(transaccion);
    }
}
