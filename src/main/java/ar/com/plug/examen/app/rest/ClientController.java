package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.aspect.LogginAspect;
import ar.com.plug.examen.domain.exceptions.ClientDoesNotExistException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientServiceImpl service;

    @GetMapping(value = "/getClients")
    @LogginAspect
    public ResponseEntity<?> getClients(){
        List<Client> clients = this.service.findAll();
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping(value = "/getClient/{id}")
    @LogginAspect
    public ResponseEntity<?> getClient(@PathVariable("id") Long id){
        Client client = null;
        try {
            client = this.service.findById(id);
        } catch (ClientDoesNotExistException e) {
            return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(client);
    }

    @PostMapping(value="/client")
    public ResponseEntity<?> createClient(@RequestBody Client aClient){
        Client client = this.service.saveClient(aClient);
        return ResponseEntity.ok().body(client);
    }

    @PutMapping(value = "/client/{id}")
    public ResponseEntity<?> updateClient(@RequestBody Client aClient){
        Client client = null;
        try {
            client = this.service.updateClient(aClient);
        } catch (ClientDoesNotExistException e) {
            return new ResponseEntity<>("The client does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(client);
    }

}
