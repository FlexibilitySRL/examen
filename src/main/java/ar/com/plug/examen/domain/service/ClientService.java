package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.domain.model.Client;

public interface ClientService
{
	PageDto<Client> getActiveClientsPageable(int pageNumber, int pageSize);

	PageDto<Client> getAllClients(int pageNumber, int pageSize);

	Client getClientById(Long id);

	Client getClientByDocumentNumber(String document);
}
