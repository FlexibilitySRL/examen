package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Compra;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.repository.ClienteRepository;
import ar.com.plug.examen.domain.repository.CompraRepository;
import ar.com.plug.examen.domain.repository.ProductoRepository;
import ar.com.plug.examen.domain.repository.TransaccionRepository;
import ar.com.plug.examen.domain.service.CompraService;
import ar.com.plug.examen.domain.service.TransaccionService;
import ar.com.plug.examen.dto.responses.ListaComprasResponse;
import ar.com.plug.examen.dto.responses.TransaccionResponse;
import ar.com.plug.examen.enums.EstadosComprasEnum;
import ar.com.plug.examen.enums.EstadosTransaccionEnum;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransaccionServiceImpl implements TransaccionService {
    
    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CompraService compraService;

    @Override
    public ResponseEntity startTransaccion(Long clienteId) {
        Transaccion transaccion = new Transaccion();
        transaccion.setEstado(EstadosTransaccionEnum.EN_PROCESO.name());
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if(Objects.nonNull(cliente)){
        transaccion.setCliente(cliente);
        }
        else{
            throw new RuntimeException("El cliente con identificador "+ clienteId + "no fue encontrado");
        }
        return ResponseEntity.ok(transaccionRepository.save(transaccion));

    }

    @Override
    public List<Transaccion> listAllTransacciones() {
        return transaccionRepository.findAll();
    }

    @Override
    public TransaccionResponse endTransaccion(Long transaccionId) {
        if(transaccionActivo(transaccionId)){
            ListaComprasResponse listaCompras = findAllByTransaccion(transaccionId);
            if(listaCompras != null) {
                TransaccionResponse transaccionResponse = new TransaccionResponse();
                for (Compra compra : listaCompras.getCompras()) {
                    compra.setEstado(EstadosComprasEnum.APROBADO.name());
                    compraRepository.save(compra);
                }
                transaccionResponse.setCompras(listaCompras.getCompras());
                transaccionResponse.setIdTransaccion(listaCompras.getTransaccionId());
                transaccionResponse.setEstado(EstadosTransaccionEnum.FINALIZADO.name());
                transaccionResponse.setCliente(listaCompras.getCompras().get(0).getTransaccion().getCliente());
                Long valorTotal = 0L;
                for (Compra compra : listaCompras.getCompras()) {
                        valorTotal = valorTotal + compra.getProducto().getPrecio()
                                * compra.getCantidad();
                }
                transaccionResponse.setTotalTransaccion(valorTotal);
                Transaccion transaccion = transaccionRepository.findById(transaccionId).orElse(null);
                saveTransaccion(transaccionResponse, transaccion);
                return transaccionResponse;
            }
            else {
                throw new RuntimeException("La transaccion con identificador " + transaccionId + "no existe.");
            }
        }else{
            throw new RuntimeException("Transaccion ya en estado FINALIZADO. No se puede hacer checkout.");
        }
    }

    @Override
    public Boolean transaccionActivo(Long transaccionId) {
        Transaccion transaccion = transaccionRepository.findById(transaccionId).orElse(null);
        if(transaccion != null) {
            if(Objects.isNull(transaccion.getEstado()) || transaccion.getEstado().isEmpty() || !transaccion.getEstado().equals(EstadosTransaccionEnum.FINALIZADO.name())){
                return true;
            }
            else {
                return false;
            }
        }
        else throw new RuntimeException("La transacción con identificador " + transaccionId + "no existe.");
    }

    @Override
    public ResponseEntity saveTransaccion(TransaccionResponse transaccionResponse, Transaccion transaccion) {
        if(transaccion != null){
            transaccion.setEstado(transaccionResponse.getEstado());
            transaccion.setTotalTransaccion(transaccionResponse.getTotalTransaccion());
            transaccion.setCliente(transaccionResponse.getCliente());
            return ResponseEntity.ok(transaccionRepository.save(transaccion));
        }
        else {
            throw new RuntimeException("Transaccion no encontrada");
        }
    }

    @Override
    public ListaComprasResponse findAllByTransaccion(Long transaccionId) {
        List<Compra> compras = compraRepository.findAllByTransaccionId(transaccionId);
        ListaComprasResponse listaComprasResponse = new ListaComprasResponse();

        listaComprasResponse.setCompras(compras);
        listaComprasResponse.setTransaccionId(transaccionId);

        return listaComprasResponse;
    }
}
