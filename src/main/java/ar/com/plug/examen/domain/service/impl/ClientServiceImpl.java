/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repositories.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author msulbara
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Set<Client> findAll() {
        Set<Client> clients = new HashSet<>();
        clientRepository.findAll().iterator().forEachRemaining(clients::add);
        return clients;
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Client save(Client object) {
        return clientRepository.save(object);
    }

    @Override
    public Client update(Client object) {
        Client client = findById(object.getId());
        
        if(client == null) {
            throw new RuntimeException("client does not exist");
        } else {
            return save(object);
        }
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void delete(Client object) {
        clientRepository.delete(object);
    }

}
