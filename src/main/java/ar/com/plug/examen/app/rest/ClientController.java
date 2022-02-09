package ar.com.plug.examen.app.rest;


import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.utils.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> listClients(){
        List<Client> clients = new ArrayList<>();
        clients = clientService.listAllClient();
        if (clients.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clients);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {
        Optional<Client> client = Optional.ofNullable(clientService.getClient(id));
        if (client.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client.get());
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MessageFormat.formatMessage(result));
        }
        Optional<Client> clientCreate =  Optional.ofNullable(clientService.createClient(client));
        if(clientCreate.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect or duplicate parameters");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clientCreate.get());
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id,@RequestBody Client client){
        client.setId(id);
        Optional<Client> clientDB = Optional.ofNullable(clientService.updateClient(client));
        if (clientDB.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientDB.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id){
        Optional<Client> clientDelete = Optional.ofNullable(clientService.deleteClient(id));
        if (clientDelete.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientDelete.get());
    }
}
