package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") long id) {
        Optional<Client> client = clientService.findById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client request) {
        Client client = clientService.save(new Client(request.getFirstName(), request.getLastName()));
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") long id, @Valid @RequestBody Client request) {
        Optional<Client> clientData = clientService.findById(id);
        if (clientData.isPresent()) {
            Client client = clientData.get();
            client.setFirstName(request.getFirstName());
            client.setLastName(request.getLastName());
            return new ResponseEntity<>(clientService.save(client), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
