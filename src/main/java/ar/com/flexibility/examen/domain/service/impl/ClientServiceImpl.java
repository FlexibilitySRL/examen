package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.mappers.AutoMapperApiEntity;
import ar.com.flexibility.examen.domain.mappers.AutoMapperEntityApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repositories.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

    private ClientRepository clientRepository;
    private AutoMapperEntityApi autoMapperEntityApi;
    private AutoMapperApiEntity autoMapperApiEntity;
	private Log log = LogFactory.getLog(ClientServiceImpl.class);
	
	
	@Override
	public ClientApi create(ClientApi clientApi) {
		Client client = autoMapperApiEntity.sourceToDestinationClientApi(clientApi);
        clientRepository.save(client);
        log.debug("Client created");
        return autoMapperEntityApi.sourceToDestinationClient(client);
	}

	@Override
	public ClientApi get(Long id) {
		Client client = null;
		try {
			client = clientRepository.findById(id)
			        .orElseThrow(() -> new NotFoundException());
	        log.debug("Get Client");
		} catch (NotFoundException e) {
	        log.debug("Cliente not found");
		}
        return autoMapperEntityApi.sourceToDestinationClient(client);
	}

	@Override
	public List<ClientApi> getClients() {
		List<Client> clients = clientRepository.findAll();
        return autoMapperEntityApi.sourceToDestinationClients(clients);
	}

	@Override
	public void delete(Long id) {
        clientRepository.delete(id);
	}

	@Override
	public ClientApi update(Long id, ClientApi clientApi) {
		Client client = null;
		try {
			client = clientRepository.findById(id)
			        .orElseThrow(() -> new NotFoundException());
	        client.setName(clientApi.getName());
	        clientRepository.saveAndFlush(client);
	        log.debug("Update Client");
		} catch (NotFoundException e) {
	        log.debug("Cliente not found");
		}
        return autoMapperEntityApi.sourceToDestinationClient(clientRepository.save(client));
	}

}
