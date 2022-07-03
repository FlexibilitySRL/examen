package ar.com.plug.examen.app.rest.services.impl;


import ar.com.plug.examen.app.api.ClientDto;
import ar.com.plug.examen.app.api.ProductDto;
import ar.com.plug.examen.app.rest.model.Product;
import ar.com.plug.examen.app.rest.repositories.ClientRepository;
import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.rest.model.Client;
import ar.com.plug.examen.app.rest.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<Client> optionalClient = this.clientRepository.findById(id);
        if(optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new NoSuchElementException("El id del cliente no se encuentra en la base de datos.");
        }
    }

    @Override
    public Client saveClient(ClientDto clientDto) throws ValidationException
    {
        if(Objects.isNull(clientDto)) {
            throw new ValidationException("Los datos para la creación de un cliente no pueden ser nulos.");
        }
        Client client;
        if(clientDto.getId()!=null){
            //Edit
            client = this.clientRepository.findById(clientDto.getId()).orElseThrow(()->new NoSuchElementException("Client With Id " + clientDto.getId() + " doesn't exist"));
            client.setName(clientDto.getName());
            client.setEmail(clientDto.getEmail());
            client.setActive(clientDto.getActive());
        }else{
            client = new Client(clientDto.getName(), clientDto.getEmail(), clientDto.getActive() );
        }
        return this.clientRepository.save(client);

    }

    @Override
    public List<Client> bulkSaveClient(List<ClientDto> clientDtos) throws ValidationException
    {
        if(Objects.isNull(clientDtos)) {
            throw new ValidationException("Cannot store empty object.");
        }
        List<Client> listClients = clientDtos.stream().map(ClientDto::toClient).collect(Collectors.toList());
        return this.clientRepository.saveAll(listClients);
    }

    @Override
    public Boolean inactivateClient(Long id) throws ValidationException
    {
        if(Objects.isNull(id)) {
            throw new ValidationException("Los datos para la actualización de un cliente no pueden ser nulos.");
        }

        Client clientFromDatabase = this.clientRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("El cliente con el id " + id + " no existe.")
                );
        clientFromDatabase.setActive(Boolean.FALSE);
        this.clientRepository.save(clientFromDatabase);
         return Boolean.TRUE;
    }
}