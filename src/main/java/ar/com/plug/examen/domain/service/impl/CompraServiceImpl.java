package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Compra;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.repository.CompraRepository;
import ar.com.plug.examen.domain.repository.ProductoRepository;
import ar.com.plug.examen.domain.repository.TransaccionRepository;
import ar.com.plug.examen.domain.service.CompraService;
import ar.com.plug.examen.dto.requests.CompraRequest;
import ar.com.plug.examen.enums.EstadosComprasEnum;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Override
    public ResponseEntity addCompra(CompraRequest compraRequest){
        Compra compra = new Compra();
        Optional<Transaccion> transaccion = transaccionRepository.findById(compraRequest.getTransaccionId());
        Optional<Producto> producto = productoRepository.findById(compraRequest.getProductoId());
        if(producto.isPresent() && transaccion.isPresent()) {
            compra.setProducto(producto.orElse(null));
            compra.setTransaccion(transaccion.orElse(null));
            compra.setCantidad(compraRequest.getCantidad());
            compra.setEstado(EstadosComprasEnum.PENDIENTE.name());
        }
        else{
            throw new RuntimeException("Parámetros incorrectos.");
        }
        return ResponseEntity.ok(compraRepository.save(compra));
    }

    @Override
    public ResponseEntity updateCompra(CompraRequest compraRequest){
        Optional<Transaccion> transaccion = transaccionRepository.findById(compraRequest.getTransaccionId());
        Optional<Producto> producto = productoRepository.findById(compraRequest.getProductoId());
        Compra compra = compraRepository.findByProductoIdAndTransaccion(producto.get().getId(), transaccion.get()
                .getId()).get(0);

        if(compra != null) {
            compra.setProducto(producto.orElse(null));
            compra.setTransaccion(transaccion.orElse(null));
            compra.setCantidad(compraRequest.getCantidad());
            compra.setEstado(EstadosComprasEnum.PENDIENTE.name());
        }
        else{
            throw new RuntimeException("Parámetros incorrectos.");
        }
        return ResponseEntity.ok(compraRepository.save(compra));
    }

    @Override
    public ResponseEntity deleteCompraTransaccion(Long productoId, Long transaccionId) {

        List<Compra> productosComprados = compraRepository.findByProductoIdAndTransaccion(productoId, transaccionId);
        if(Objects.nonNull(productosComprados)){
            for (Compra compra: productosComprados) {
                compraRepository.delete(compra);
                return ResponseEntity.ok().build();
            }
        }
        else {
            throw new RuntimeException("El producto comprado con el identificador "+ productoId + "no existe.");
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @Override
    public ResponseEntity listCompras() {
        return ResponseEntity.ok(compraRepository.findAll());
    }

    @Override
    public List<Compra> listComprasByTransaccion(Long idTransaccion) {
        return compraRepository.findAllByTransaccionId(idTransaccion);
    }

}
