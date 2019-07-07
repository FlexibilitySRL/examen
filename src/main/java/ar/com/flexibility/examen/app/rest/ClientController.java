package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.domain.service.impl.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/client")
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


    @GetMapping("{id}")
    public ResponseEntity<?> getClient (@PathVariable Long id) {

        Client client = null;

        try {
            client = clientService.getClientById(id);
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
