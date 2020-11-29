/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientService;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author msulbara
 */
@RestController
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<Set<Client>> getClients() {
        Set<Client> clients = clientService.findAll();

        LOGGER.info("complete clients size is: {}", clients.size());

        return ResponseEntity.ok().body(clients);
    }

    @GetMapping(value = "/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {

        Client client = new Client();
        client.setId(id);
        try {
            client = clientService.findById(id);
            return ResponseEntity.ok().body(client);
        } catch (RuntimeException ex) {
            LOGGER.info("no client for id: {}", id);
            return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(
            path = "/client/create",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.save(client);
        return ResponseEntity.ok().body(savedClient);
    }

    @PutMapping(
            path = "/client/update",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        try {
            Client auxclient = clientService.update(client);
            return ResponseEntity.ok().body(auxclient);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/client/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        try {
            clientService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return new ResponseEntity<>("client does not exists", HttpStatus.NOT_FOUND);
        }
    }

}
