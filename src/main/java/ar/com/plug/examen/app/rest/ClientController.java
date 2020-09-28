package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("")
    public ResponseEntity<?> createClient (@RequestBody Client client) {
        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
    }


    @PutMapping("")
    public ResponseEntity<?> updateClient (@RequestBody Client client) {
        try {
            clientService.updateClient(client);
        } catch (ClientNotFoundException e) {
            e.printStackTrace ();
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClient (@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {
        Client client = null;
        try {
            client = clientService.getClient(id);
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
