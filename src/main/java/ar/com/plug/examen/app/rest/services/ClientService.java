package ar.com.plug.examen.app.rest.services;

import ar.com.plug.examen.app.api.ClientDto;
import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.rest.model.Client;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ClientService {

    PageDto<Client> getAllClients(int pageNumber, int pageSize);

    Client getClientById(Long id);


    Client saveClient(ClientDto clientDto) throws ValidationException;

    List<Client> bulkSaveClient(List<ClientDto> clientDto) throws ValidationException;

    Boolean inactivateClient(Long id) throws ValidationException;

}
