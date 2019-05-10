package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.exception.GenericException;
import ar.com.flexibility.examen.domain.model.Client;
import javassist.NotFoundException;

public interface ClientService
{
    void deleteAll();
    
    List<Client> findAll();
    Client findOne(Long id) throws NotFoundException;

    Client add(Client client);
    Client update(Client client) throws NotFoundException, GenericException;
    void delete(Long id) throws NotFoundException;
}
