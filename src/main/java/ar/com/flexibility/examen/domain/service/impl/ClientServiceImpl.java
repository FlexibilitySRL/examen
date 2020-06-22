package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.mappers.ApiMapper;
import ar.com.flexibility.examen.domain.mappers.EntityMapper;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private ApiMapper apiMapper;

    @Override
    public List<ClientApi> all() {
        log.trace("Retrieving all clients from client repository.");
        List<Client> clients = clientRepository.findAll();
        return entityMapper.toClientsApi(clients);
    }

    @Override
    public ClientApi get(Long id) throws NotFoundException {
        log.trace("Retrieving client from repository with id: {}", id);
        Client client = clientRepository.findOne(id);
        if (client == null)
            throw new NotFoundException("Client with id " + id);
        return entityMapper.toClientApi(client);
    }

    @Override
    public ClientApi create(ClientApi clientApi) throws BadRequestException {
        log.trace("Saving to client repository client: {}", clientApi);
        Client client = clientRepository.save(apiMapper.toClient(clientApi));
        return entityMapper.toClientApi(client);
    }

    @Override
    public ClientApi update(Long id, ClientApi clientApi) throws BadRequestException, NotFoundException {
        log.trace("Updating client with id {} in client repository with client: {}", id, clientApi);
        Client client = clientRepository.findOne(id);
        if (client == null)
            throw new NotFoundException("Client with id " + id);
        client.setName(clientApi.getName());
        return entityMapper.toClientApi(clientRepository.save(client));
    }

    @Override
    public void remove(Long id) throws NotFoundException {
        log.trace("Deleting id {} from client repository", id);
        if (!clientRepository.exists(id)) {
            throw new NotFoundException("Client id " + id + " doesn't exist");
        }
        clientRepository.delete(id);
    }

}
