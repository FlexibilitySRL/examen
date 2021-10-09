package ar.com.plug.examen.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.repository.ClienteRepository;

@Service
public class ProcessClienteServiceImpl {

    @Autowired
    ClienteRepository repo;

    public Cliente addCliente(Cliente cliente) {
        cliente.setIdCliente(0);
        return repo.save(cliente);
    }

    public Optional<Cliente> getCliente(Integer cliente) {
        return repo.findById(cliente);
    }

    public void delete(Integer cliente) {
        repo.deleteById(cliente);
    }
}
