package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.exception.GenericException;
import ar.com.flexibility.examen.domain.model.Client;
import javassist.NotFoundException;

public interface ClientService
{
    void deleteAll() throws GenericException;
    
    List<Client> findAll() throws GenericException;
    Client findOne(Long id) throws NotFoundException, GenericException;

    Client add(Client client) throws GenericException;
    Client update(Client client) throws NotFoundException, GenericException;
    void delete(Long id) throws NotFoundException, GenericException;
}
