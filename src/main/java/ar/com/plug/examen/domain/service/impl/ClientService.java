package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends GenericService<Client>{

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientRepository getRepository() {
        return repository;
    }
}
