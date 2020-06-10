package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    private ClientService clientService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientApi> createClient(@RequestBody ClientApi clientApi) {
        return new ResponseEntity<>(clientService.create(clientApi), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientApi> retrieveClient(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.retrieve(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ClientApi>> listClients() {
        return new ResponseEntity<>(clientService.list(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeClient(@PathVariable Long id) {
        clientService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientApi> updateClient(@PathVariable Long id, @RequestBody ClientApi clientApi) {
        return new ResponseEntity<>(clientService.update(id, clientApi), HttpStatus.OK);
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}
