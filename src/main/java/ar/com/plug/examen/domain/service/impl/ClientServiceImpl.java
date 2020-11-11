package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.repository.ClientRepository;
import ar.com.plug.examen.domain.exceptions.ClientDoesNotExistException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public List<Client> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Client saveClient(Client aClient) {
        return this.repository.save(aClient);
    }

    @Override
    public Client findById(Long id) throws ClientDoesNotExistException {
        Client aClient =
                this.repository.findById(id)
                        .orElseThrow(()-> new ClientDoesNotExistException("The client with id: " + id.toString() + " does not exist."));

        return aClient;
    }

    @Override
    public Client updateClient(Client aClient) throws ClientDoesNotExistException {
        Client client =this.findById(aClient.getId());
        return this.saveClient(client);
    }

    @Override
    public void deleteClient(Long id) throws ClientDoesNotExistException {
        Client aClient = this.findById(id);
        this.repository.delete(aClient);
    }
}
