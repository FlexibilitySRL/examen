package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.AbstractService;
import ar.com.flexibility.examen.domain.service.ClientService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultClientService extends AbstractService<Client, ClientRepository> implements ClientService {

    private static final Boolean NON_DELETED_FLAG = false;

    @Autowired
    public DefaultClientService(ClientRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> listAll() {
        return Lists.newArrayList(repository.findByDeleted(NON_DELETED_FLAG));
    }
}
