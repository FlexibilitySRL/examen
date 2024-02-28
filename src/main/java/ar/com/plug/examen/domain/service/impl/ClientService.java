package ar.com.plug.examen.domain.service.impl;


import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService{

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client getById(Long id){
        return repository.findById(id).get();
    }

    public Client saveOrUpdate(Client client) {
        return this.repository.save(client);
    }

    public void delete(Long id) {
        Client client = this.getById(id);
        this.repository.delete(client);
    }

    public List<Client> getAll() {
        return this.repository.findAll();
    }
}
