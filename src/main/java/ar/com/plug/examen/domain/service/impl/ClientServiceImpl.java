package ar.com.plug.examen.domain.service.impl;

import java.util.NoSuchElementException;
import java.util.Objects;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService
{
	private final ClientRepository clientRepository;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository)
	{
		this.clientRepository = clientRepository;
	}

	@Override
	public PageDto<Client> getActiveClientsPageable(int pageNumber, int pageSize)
	{
		return new PageDto<>(
			clientRepository.findAllByActiveTrue(PageRequest.of(pageNumber, pageSize))
		);
	}

	@Override
	public PageDto<Client> getAllClients(int pageNumber, int pageSize)
	{
		return new PageDto<>(
			this.clientRepository.findAll(PageRequest.of(pageNumber, pageSize))
		);
	}

	@Override
	public Client getClientById(Long id)
	{
		if(Objects.isNull(id)) {
			throw new NoSuchElementException("El id del cliente no puede ser nulo.");
		}
		return this.clientRepository.getOne(id);
	}

	@Override
	public Client getClientByDocumentNumber(String document)
	{
		if(StringUtils.isBlank(document)) {
			throw new NoSuchElementException("El número de documento del cliente no puede ser nulo.");
		}
		return this.clientRepository.findByDocument(document);
	}


}
