package com.example.core.service;

import com.example.core.model.Client;
import com.example.core.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository clientRepository;

    public Client create(Client client) {
        clientRepository.save(client);
        LOGGER.info("Se registro un nuevo cliente.");
        return client;
    }

    /***
     * Se realizan bajas logicas
     * @param id
     */
    public void delete(Long id) {
        clientRepository.update(id, Boolean.FALSE);
        LOGGER.info("Se dio de baja exitosamente.");
    }

    public Client update(Client client) {
        clientRepository.save(client);
        LOGGER.info("Se actualizan los datos del cliente.", client.getUsername());
        return client;
    }

    public Client information(String username) {
        return clientRepository.findClientByUsername(username);
    }
}
