package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.service.ClienteService;
import ar.com.plug.examen.exception.MicroserviceErrorException;
import ar.com.plug.examen.infrastructure.repository.IClienteRepository;
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
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    IClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() throws MicroserviceErrorException {
        try {
            log.info("Consultando todos los clientes");
            return clienteRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MicroserviceErrorException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Integer id) {
        log.info("Buscando cliente con el ID: " + id);
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) throws MicroserviceErrorException {
        try {
            log.info("Guardando cliente: " + cliente.getEmail());
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MicroserviceErrorException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Cliente update(Cliente cliente, Integer id) throws MicroserviceErrorException {
        try {
            Optional<Cliente> clienteData = clienteRepository.findById(id);
            if (clienteData.isPresent()) {
                Cliente c = clienteData.get();
                c.setNombre(cliente.getNombre());
                c.setApellido(cliente.getApellido());
                c.setEmail(cliente.getEmail());
                c.setCreateAt(cliente.getCreateAt());

                c.setFoto(cliente.getFoto());
                return clienteRepository.save(c);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MicroserviceErrorException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Integer id) throws MicroserviceErrorException {
        try {
            log.info("Eliminando cliente con el ID: " + id);
            clienteRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MicroserviceErrorException(e.getMessage());
        }
    }
}