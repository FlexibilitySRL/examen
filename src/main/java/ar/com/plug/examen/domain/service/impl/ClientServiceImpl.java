package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.dto.ClientDto;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.mappers.ClientMapper;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import java.util.List;

import ar.com.plug.examen.domain.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    private Validator validator;

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.ClientService#findAll()
     */
    @Override
    public List<ClientDto> findAll() {
        return clientMapper.clientsToListClientApi(clientRepository.findAll());
    }

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.ClientService#findById(Long) ()
     */
    @Override
    public ClientDto findById(Long id) throws GenericNotFoundException {
        return this.clientMapper.clientToClientApi(this.clientRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("Client not found")));
    }

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.ClientService#save(ClientDto) ()
     */
    @Override
    public ClientDto save(ClientDto clientApi) throws GenericBadRequestException {
        validator.validateClient(clientApi, Boolean.FALSE);
        return this.clientMapper.clientToClientApi(
                this.clientRepository.save(this.clientMapper.clientApiToClient(clientApi)));
    }

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.ClientService#update(ClientDto) ()
     */
    @Override
    public ClientDto update(ClientDto clientApi)
            throws GenericNotFoundException, GenericBadRequestException {
        validator.validateClient(clientApi, Boolean.TRUE);
        this.clientRepository.findById(clientApi.getId())
                .orElseThrow(() -> new GenericNotFoundException("Client not found"));
        return this.clientMapper.clientToClientApi(
                this.clientRepository.save(this.clientMapper.clientApiToClient(clientApi)));
    }

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.ClientService#delete(Long) ()
     */
    @Override
    public void delete(Long id) throws GenericNotFoundException {
        this.clientRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("Client not found"));
        this.clientRepository.deleteById(id);
    }
}
