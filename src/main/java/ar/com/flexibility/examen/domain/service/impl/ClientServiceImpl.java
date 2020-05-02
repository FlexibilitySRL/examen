package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = Logger.getLogger(ClientServiceImpl.class);

    ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> retrieveClients() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Client retrieveClientById(Long id) {
        Client client = clientRepository.findOne(id);

        if (client == null) {
            logger.trace(String.format("Could not retrieve client with id %s", id));
        }

        return client;
    }

    @Override
    public Client addClient(Client client) {
        Client newClient = clientRepository.save(client);

        if (newClient == null) {
            logger.trace(String.format("Could not create the client %s", client.getFullName()));
        }

        return newClient;
    }

    @Override
    public Client updateClient(Long id, Client client) {
        if (!clientRepository.exists(id)) {
            logger.trace(String.format("Could not update the client with id %s. It does not exist.", id));
            return null;
        }

        client.setId(id);
        Client updatedClient = clientRepository.save(client);

        if (updatedClient == null) {
            logger.trace(String.format("Could not update the client with id %s", client.getId()));
        }

        return updatedClient;
    }

    @Override
    public boolean deleteClient(Long id) {
        if (!clientRepository.exists(id)) {
            logger.trace(String.format("Could not delete the client with id %s. It does not exist.", id));
            return false;
        }

        try {
            clientRepository.delete(id);
        } catch (Exception e) {
            logger.trace(String.format("Could not delete the client with id %s. An internal error occurred: %s.",
                    id, e.getMessage()));
            return false;
        }

        return true;
    }
}
