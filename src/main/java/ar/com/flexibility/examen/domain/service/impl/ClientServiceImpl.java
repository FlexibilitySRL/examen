package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.mappers.EntityApiMapper;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repositories.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private ClientRepository clientRepository;
    private EntityApiMapper entityApiMapper;

    @Override
    public ClientApi create(ClientApi clientApi) {
        logger.debug("Validating required data...");
        if (StringUtils.isBlank(clientApi.getName())) {
            logger.error("The name is required");
            throw new BadRequestException("Required data is missing: name");
        }
        Client client = entityApiMapper.destinationToSourceClientApi(clientApi);
        clientRepository.save(client);

        logger.info("Client created {}", client.getId());
        return entityApiMapper.sourceToDestinationClient(client);
    }

    @Override
    public ClientApi retrieve(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("client with id " + id));

        return entityApiMapper.sourceToDestinationClient(client);
    }

    @Override
    public List<ClientApi> list() {
        List<Client> clients = clientRepository.findAll();
        return entityApiMapper.sourceToDestinationClients(clients);
    }

    @Override
    public void remove(Long id) {
        if (!clientRepository.exists(id)) {
            throw new NotFoundException("client with id " + id);
        }
        clientRepository.delete(id);
    }

    @Override
    public ClientApi update(Long id, ClientApi clientApi) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("client with id " + id));

        client.setName(clientApi.getName());
        return entityApiMapper.sourceToDestinationClient(clientRepository.save(client));
    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setEntityApiMapper(EntityApiMapper entityApiMapper) {
        this.entityApiMapper = entityApiMapper;
    }
}
