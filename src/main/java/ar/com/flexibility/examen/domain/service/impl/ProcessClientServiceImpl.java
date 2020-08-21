package ar.com.flexibility.examen.domain.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ProcessClientService;
import ar.com.flexibility.examen.repository.ClientRepository;

@Service
public class ProcessClientServiceImpl implements ProcessClientService {

	private final Logger log = LoggerFactory.getLogger(ProcessClientServiceImpl.class);

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public ClientApi create(ClientApi clientApi) {
		Client client = modelMapper.map(clientApi, Client.class);
		client = clientRepository.saveAndFlush(client);
		clientApi = modelMapper.map(client, ClientApi.class);
		log.info("Client successfully created.");
		return clientApi;
	}

	@Override
	public ClientApi update(Long clientId, ClientApi clientApi) {
		Client client = modelMapper.map(clientApi, Client.class);
		client = clientRepository.findById(clientId);
		client.setFirstName(clientApi.getFirstName());
		client.setLastName(clientApi.getLastName());
		client.setCategory(clientApi.getCategory());
		client = clientRepository.saveAndFlush(client);
		clientApi = modelMapper.map(client, ClientApi.class);
		log.info("Client successfully updated.");
		return clientApi;
	}

	@Override
	public String delete(ClientApi clientApi) {
		Client client = modelMapper.map(clientApi, Client.class);
		clientRepository.delete(client);
		log.info("Client successfully deleted.");
		return "Client successfully deleted.";
	}
}
