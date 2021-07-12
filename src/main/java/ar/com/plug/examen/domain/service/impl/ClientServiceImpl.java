package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.aspects.LogAnnotation;
import ar.com.plug.examen.domain.Repository.ClientRepository;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.mappers.ClientMapper;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.validators.Validator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@LogAnnotation
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ClientMapper clientMapper;

  @Autowired
  private Validator validator;

  /**
   * (non-Javadoc)
   *
   * @see ar.com.plug.examen.domain.service.ClientService#findAll()
   */
  @Override
  public List<ClientApi> findAll() {
    return this.clientMapper.clientsToListClientApi(this.clientRepository.findAll());
  }

  /**
   * (non-Javadoc)
   *
   * @see ar.com.plug.examen.domain.service.ClientService#findByIdChecked(Long) ()
   */
  @Override
  public ClientApi findByIdChecked(Long id) throws GenericNotFoundException {
    return this.clientMapper.clientToClientApi(this.clientRepository.findById(id)
        .orElseThrow(() -> new GenericNotFoundException("Client not found")));
  }

  /**
   * (non-Javadoc)
   *
   * @see ar.com.plug.examen.domain.service.ClientService#save(ClientApi) ()
   */
  @Override
  @Transactional
  public ClientApi save(ClientApi clientApi) throws GenericBadRequestException {
    this.validator.validateClient(clientApi, Boolean.FALSE);
    return this.clientMapper.clientToClientApi(
        this.clientRepository.save(this.clientMapper.clientApiToClient(clientApi)));
  }

  /**
   * (non-Javadoc)
   *
   * @see ar.com.plug.examen.domain.service.ClientService#update(ClientApi) ()
   */
  @Override
  @Transactional
  public ClientApi update(ClientApi clientApi)
      throws GenericNotFoundException, GenericBadRequestException {
    this.validator.validateClient(clientApi, Boolean.TRUE);
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
  @Transactional
  public void delete(Long id) throws GenericNotFoundException {
    this.clientRepository.findById(id)
        .orElseThrow(() -> new GenericNotFoundException("Client not found"));
    this.clientRepository.deleteById(id);
  }

}
