package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController for client entity
 */
@RestController
@RequestMapping(path = "/clients")
public class ClientController {

  @Autowired
  private ClientService clientService;

  /**
   * Get all clients
   * @return List<ClientApi>
   */
  @GetMapping()
  public ResponseEntity<List<ClientApi>> getClients() {
    return new ResponseEntity<>(this.clientService.findAll(), HttpStatus.OK);
  }

  /**
   * Get client by id
   * @param id
   * @return ClientApi
   * @throws GenericNotFoundException
   */
  @GetMapping("/findById/{id}")
  public ResponseEntity<ClientApi> getClientById(@PathVariable("id") Long id) throws GenericNotFoundException {
    return new ResponseEntity<>(this.clientService.findByIdChecked(id), HttpStatus.OK);
  }

  /**
   * Save a client
   * @param clientApi
   * @return ClientApi
   * @throws GenericBadRequestException
   */
  @PostMapping()
  public ResponseEntity<ClientApi> save(@RequestBody ClientApi clientApi) throws GenericBadRequestException {
    return new ResponseEntity<>(this.clientService.save(clientApi), HttpStatus.CREATED);
  }

  /**
   * Update a client
   * @param clientApi
   * @return ClientApi
   * @throws GenericNotFoundException
   * @throws GenericBadRequestException
   */
  @PutMapping()
  public ResponseEntity<ClientApi> update(@RequestBody ClientApi clientApi) throws GenericNotFoundException, GenericBadRequestException {
    return new ResponseEntity<>(this.clientService.update(clientApi), HttpStatus.OK);
  }

  /**
   * Delete a client by id
   * @param id
   * @throws GenericNotFoundException
   */
  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") Long id) throws GenericNotFoundException {
    this.clientService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
