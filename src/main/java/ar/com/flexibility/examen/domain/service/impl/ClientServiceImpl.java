package ar.com.flexibility.examen.domain.service.impl;

import static ar.com.flexibility.examen.domain.exception.GenericException.GENERAL_ID_NOT_EXIST_FORMATED;
import static ar.com.flexibility.examen.domain.exception.GenericException.GENERAL_NOT_CHANGES_TO_MADE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.exception.GenericException;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import javassist.NotFoundException;

@Service
public class ClientServiceImpl implements ClientService
{
	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public void deleteAll()
	{
		clientRepository.deleteAll();
	}

	@Override
	public List<Client> findAll()
	{
		return clientRepository.findAll();
	}

	@Override
	public Client findOne(Long id) throws NotFoundException
	{
		Client client = clientRepository.findOne(id);
		if (client == null)
			throw new NotFoundException(String.format(GENERAL_ID_NOT_EXIST_FORMATED, id));
		return client;
	}

	@Override
	public Client add(Client client)
	{
		client.setId(null);
		return clientRepository.saveAndFlush(client);
	}

	@Override
	public Client update(Client client) throws NotFoundException, GenericException
	{
		Client clientToPersist = findOne(client.getId());

		if (clientToPersist.equals(client))
			throw new GenericException(GENERAL_NOT_CHANGES_TO_MADE);

		clientToPersist.setFullname(client.getFullname());
		clientToPersist.setEmail(client.getEmail());

		return clientRepository.saveAndFlush(clientToPersist);

	}

	@Override
	public void delete(Long id) throws NotFoundException
	{
		id = findOne(id).getId();
		clientRepository.delete(id);
	}
}
