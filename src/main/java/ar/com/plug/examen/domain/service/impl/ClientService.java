package ar.com.plug.examen.domain.service.impl;


import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService{

    private static final Logger LOGGER =  LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client getById(Long id){
        LOGGER.info("ClientService.getById() id :{}",id);
        return repository.findById(id).orElseThrow( () -> new NotFoundException("Entity with id "+id+" not found"));
    }

    public Client saveOrUpdate(Client client) {
        LOGGER.info("ClientService.saveOrUpdate() client :{}",client);
        return this.repository.save(client);
    }

    public void delete(Long id) {
        LOGGER.info("ClientService.delete() id :{}",id);
        Client client = this.getById(id);
        this.repository.delete(client);
    }

    public List<Client> getAll() {
        LOGGER.info("ClientService.delete() getAll");
        return this.repository.findAll();
    }
}
