package ar.com.flexibility.examen.app.rest;

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

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.service.ClientService;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/createProduct")
    public ResponseEntity<ClientApi> createClient(@RequestBody ClientApi clientApi) {
        return new ResponseEntity<>(clientService.create(clientApi), HttpStatus.CREATED);
    }

    @GetMapping("/getClient/{id}")
    public ResponseEntity<ClientApi> getClient(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.get(id), HttpStatus.OK);
    }

    @GetMapping("/getClients")
    public ResponseEntity<List<ClientApi>> getClients() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
    	clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/updateClient/{id}")
    public ResponseEntity<ClientApi> updateClient(@PathVariable Long id, @RequestBody ClientApi clientApi) {
        return new ResponseEntity<>(clientService.update(id, clientApi), HttpStatus.OK);
    }
}
