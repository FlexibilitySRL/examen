package ar.com.flexibility.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.StatusApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.exception.ClientException;
import ar.com.flexibility.examen.exception.ProductException;

@Service
public class ClientServiceImpl implements ClientService
{

    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClientApi add(ClientApi clientApi) throws ClientException
    {
        if (clientApi.getNombre() == null)
        {
            throw new ClientException(ClientException.E001,
                    "Name cannot be null");
        }
        if (clientApi.getApellido() == null)
        {
            throw new ClientException(ClientException.E001,
                    "Surname cannot be null");
        }
        Client client = new Client(clientApi);

        try
        {
            clientRepository.saveAndFlush(client);
            return new ClientApi(client);
        }
        catch (Exception e)
        {
            throw new ClientException(ClientException.E001, e.getMessage());
        }

    };

    @Override
    public ClientApi update(ClientApi clientApi) throws ClientException
    {

        Client client = new Client(clientApi);

        try
        {
            client = clientRepository.findOne(client.getId());
        }
        catch (Exception e)
        {
            throw new ClientException(ClientException.E001,
                    "Client not found.");
        }

        if (client == null)
        {
            throw new ClientException(ClientException.E001,
                    "Client not found.");
        }
        try
        {
            client.setName(clientApi.getNombre());
            client.setSurname(clientApi.getApellido());

            clientRepository.saveAndFlush(client);

            return new ClientApi(client);
        }
        catch (Exception e)
        {
            throw new ClientException(ClientException.E001,
                    "Error updating client.");
        }

    };

    @Override
    public ClientApi remove(Long id) throws ClientException
    {
        Client client;

        try
        {
            client = clientRepository.findOne(id);
            if (client == null)
            {
                throw new ClientException(ClientException.E001,
                        "Client not found.");
            }
            clientRepository.delete(client);
            return new ClientApi(client);
        }
        catch (Exception e)
        {
            throw new ClientException(ClientException.E001, e.getMessage());
        }
    }

    @Override
    public ClientApi get(Long id) throws ClientException
    {
        Client client;
        try
        {
            client = clientRepository.findOne(id);
            if (client != null)
            {
                return new ClientApi(client);
            }
            else
            {
                throw new ClientException(ClientException.E001,
                        "Client not found.");
            }

        }
        catch (Exception e)
        {
            throw new ClientException(ClientException.E001, e.getMessage());
        }

    }

    @Override
    public List<ClientApi> getAll(Long page, Long pageSize)
            throws ClientException
    {

        if (page == null)
        {
            page = 0L;
        }
        if (pageSize == null)
        {
            pageSize = 10L;
        }
        if (page < 0L)
        {
            throw new ClientException(ClientException.E002,
                    "Page must be greater than or equal to zero.");
        }
        if (pageSize <= 0L)
        {
            throw new ClientException(ClientException.E002,
                    "Page size must be greater than zero.");
        }
        Page<Client> clients;
        try
        {
            clients = clientRepository.findAll(new PageRequest(page.intValue(),
                    pageSize.intValue()));
        }
        catch (Exception e)
        {
            throw new ClientException(ClientException.E002,
                    "Solicited info was not found.");
        }

        try
        {
            List<ClientApi> clientApiList = new ArrayList<ClientApi>();

            for (Client client : clients)
            {
                clientApiList.add(new ClientApi(client));
            }
            return clientApiList;
        }
        catch (Exception e)
        {
            throw new ClientException(ClientException.E001, e.getMessage());
        }

    }

}
