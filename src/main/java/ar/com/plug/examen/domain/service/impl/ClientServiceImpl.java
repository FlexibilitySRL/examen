package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.execptions.BadRequestException;
import ar.com.plug.examen.domain.execptions.NotFoundException;
import ar.com.plug.examen.domain.mappers.ClientMapper;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repositories.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.validations.PairResult;
import ar.com.plug.examen.domain.validations.Validation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ClientMapper clientMapper;
	@Autowired
	private Validation validation;

	@Override
	public ClientApi createClient(ClientApi clientApi) {
		
		PairResult result = new PairResult(false, null);
		
		result = validation.validateClient(clientApi);
		
		if(!result.isValid()) {
			log.error(result.getLeyend() + " for the creation of the client. ");
			throw new BadRequestException("Mandatory data is missing: " + result.getLeyend());
		}

		Client client = clientRepository.save(clientMapper.fillEntity(new Client(), clientApi));

		log.info("The client " + client.getId() +" was succesfully created.");
		
		return clientMapper.getDto(client);
	}  

	@Override
	public ClientApi getClientById(Long id) {
		
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Client with the id:" + id + " was not found."));
		
		return clientMapper.getDto(client);
	}

	@Override
	public List<ClientApi> listAllClients() {
		
		List<Client> clients = clientRepository.findAll();
		
		if(clients.isEmpty()) {
			log.info("The list of clients is empty");
		}
		
		return clientMapper.getDto(clients);
	}

	@Override
	public void removeClient(Long id) {
		
		if(!clientRepository.existsById(id)) {
			log.error("The cliente with the id:" + id + " does not exist.");
			throw new NotFoundException("The client with id " + id + " does not exist");
		}
		
		clientRepository.deleteById(id);
		
		log.info("The client " + id +" was succesfully deleted.");
	}

	@Override
	public ClientApi updateClient(Long id, ClientApi clientApi) {
		
		if(!clientRepository.existsById(id)) {
			log.error("The cliente with the id:" + id + " does not exist.");
			throw new NotFoundException("client with id " + id + " does not exist");
		} 
		
		validation.validateId(id, clientApi.getId());
		
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Client with the id:" + id + " was not found."));
		
		client.setId(id);
		client.setName(clientApi.getName());
		
		client = clientRepository.save(clientMapper.fillEntity(new Client(), clientApi));
		
		log.info("The client " + id +" was succesfully updated.");
		
		return clientMapper.getDto(client);
	}

	

}
