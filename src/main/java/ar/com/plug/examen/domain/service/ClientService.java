package ar.com.plug.examen.domain.service;

import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.ClientDto;
import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.domain.model.Client;

public interface ClientService
{
	PageDto<Client> getActiveClientsPageable(int pageNumber, int pageSize);

	PageDto<Client> getAllClients(int pageNumber, int pageSize);

	Client getClientById(Long id);

	Client getClientByDocumentNumber(String document);

	Client saveClient(ClientDto clientDto) throws ValidationException;

	Client updateClient(Long id, ClientDto clientDto) throws ValidationException;

	Client inactivateClient(Long id) throws ValidationException;

	Long deleteClient(Long id) throws ValidationException;
}
