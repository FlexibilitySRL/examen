package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.service.ProductoService;
import ar.com.plug.examen.exception.MicroserviceErrorException;
import ar.com.plug.examen.infrastructure.repository.IProductoRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Log4j2
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    IProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public Producto findProductoById(Integer id) {
        log.info("Buscando el producto con el ID: " + id);
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Producto saveProducto(Producto producto) throws MicroserviceErrorException {
        try {
            log.info("Guardando producto.");
            return productoRepository.save(Producto
                    .builder()
                    .nombre(producto.getNombre())
                    .precio(producto.getPrecio())
                    .createdAt(new Date())
                    .build());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MicroserviceErrorException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Producto updateProducto(Producto producto, Integer id) throws MicroserviceErrorException {
        try {
            log.info("Actualizando producto con el ID: " + id);
            Optional<Producto> productoData = productoRepository.findById(id);
            if (productoData.isPresent()) {
                Producto p = productoData.get();
                p.setNombre(producto.getNombre());
                p.setPrecio(producto.getPrecio());
                return productoRepository.save(p);
            } else {
                log.warn("El producto con el ID: " + id + " no existe");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MicroserviceErrorException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteProductoById(Integer id) throws MicroserviceErrorException {
        try {
            log.info("Eliminando producto con el ID: " + id);
            productoRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MicroserviceErrorException(e.getMessage());
        }
    }
}