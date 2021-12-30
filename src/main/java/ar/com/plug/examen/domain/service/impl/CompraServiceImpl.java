package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Compra;
import ar.com.plug.examen.domain.service.CompraService;
import ar.com.plug.examen.exception.MicroserviceErrorException;
import ar.com.plug.examen.infrastructure.repository.IClienteRepository;
import ar.com.plug.examen.infrastructure.repository.ICompraRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    ICompraRepository compraRepository;

    @Override
    public List<Compra> findAll() throws MicroserviceErrorException {
        try {
            log.info("Consultando todos los clientes");
            return compraRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MicroserviceErrorException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Compra findCompraById(Integer id) {
        log.info("Buscando compra con el ID: " + id);
        return compraRepository.findById(id).orElse(null);
    }

    @Override
    public Compra aprobarCompra(Integer id) throws MicroserviceErrorException {
        try {
            log.info("Aprobando la compra con el ID: " + id);
            Optional<Compra> compraData = compraRepository.findById(id);
            if (compraData.isPresent()) {
                Compra c = compraData.get();
                c.setStatus(true);
                return compraRepository.save(c);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MicroserviceErrorException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public Compra saveCompra(Compra compra) throws MicroserviceErrorException {
        try {
            log.info("Guardando compra");
            return compraRepository.save(Compra
                    .builder()
                    .descripcion(compra.getDescripcion())
                    .observacion(compra.getObservacion())
                    .cliente(compra.getCliente())
                    .items(compra.getItems())
                    .status(false)
                    .build());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MicroserviceErrorException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Compra updateCompra(Compra compra, Integer id) throws MicroserviceErrorException {
        try {
            log.info("Actualizando compra con el ID: " + id);
            Optional<Compra> compraData = compraRepository.findById(id);
            if (compraData.isPresent()) {
                Compra c = compraData.get();
                c.setDescripcion(compra.getDescripcion());
                c.setObservacion(compra.getObservacion());
                c.setCliente(compra.getCliente());
                c.setItems(compra.getItems());
                c.setStatus(compra.getStatus());
                return compraRepository.save(c);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MicroserviceErrorException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteCompraById(Integer id) throws MicroserviceErrorException {
        try {
            log.info("Eliminando compra con el ID: " + id);
            compraRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MicroserviceErrorException(e.getMessage());
        }
    }
}