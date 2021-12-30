package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.exception.MicroserviceErrorException;

import java.util.List;

public interface ClienteService {

    List<Cliente> findAll() throws MicroserviceErrorException;

    Cliente findById(Integer id);

    Cliente save(Cliente cliente) throws MicroserviceErrorException;

    Cliente update(Cliente cliente, Integer id) throws MicroserviceErrorException;

    void delete(Integer id) throws MicroserviceErrorException;

}