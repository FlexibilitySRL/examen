package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.dto.ClientDto;
import ar.com.plug.examen.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    /**
     * A description of the entire Java function.
     *
     * @param  clientApi	description of parameter
     * @return         	description of return value
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ClientDto addClient(@RequestBody ClientApi clientApi) {
        ClientDto client = clientService.addClient(clientApi);
        log.info("ClientController :: addClient :: Client added {}", client);
        return client;
    }

    /**
     * Find all clients.
     *
     * @return         list of client DTOs
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> findAll() {
        List<ClientDto> clients = clientService.findAll();
        log.info("ClientController :: findAll :: FindAll {}", clients.size());
        return clients;
    }

    /**
     * Find a client by ID.
     *
     * @param  id   The ID of the client to find
     * @return      The client DTO found by the ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable("id") Long id) {
        ClientDto client = clientService.findClientById(id);
        if (Objects.isNull(client)) {
            log.error("ClientController :: findClientById :: Client not found");
            return ResponseEntity.notFound().build();
        }
        log.info("ClientController :: findClientById :: Client found {}", client);
        return ResponseEntity.ok(client);
    }

    /**
     * updateClient function updates a client with the given id using the provided clientApi
     *
     * @param  id        the id of the client to update
     * @param  clientApi the clientApi object containing the updated client information
     * @return          the updated clientDto object
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable("id") Long id, @RequestBody ClientApi clientApi) {
        ClientDto clientUpdated = clientService.updateClient(id, clientApi);
        if (Objects.isNull(clientUpdated)) {
            log.error("ClientController :: updateClient :: Client not found");
            return ResponseEntity.notFound().build();
        }
        log.info("ClientController :: updateClient :: Client was updated {}", clientUpdated);
        return ResponseEntity.ok(clientUpdated);
    }

    /**
     * Delete a client by ID.
     *
     * @param  id   the ID of the client to delete
     * @return      the deleted client DTO
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDto> deleteClient(@PathVariable("id") Long id) {
        ClientDto clientDeleted = clientService.deleteClient(id);
        if (Objects.isNull(clientDeleted)) {
            log.error("ClientController :: deleteClient :: Client not found");
            return ResponseEntity.notFound().build();
        }
        log.info("ClientController :: deleteClient :: Client {} was deleted", id);
        return ResponseEntity.ok(clientDeleted);
    }
}
