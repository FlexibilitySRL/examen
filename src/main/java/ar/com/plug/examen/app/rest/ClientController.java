package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.exception.ClientNotFoundException;
import ar.com.plug.examen.domain.exception.ErrorResponse;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@Tag(name = "Client service", description = "Client CRUD")
@Validated
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") long id) {
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientApi clientApi) {
        return new ResponseEntity<>(clientService.save(new Client(clientApi.getFirstName(), clientApi.getLastName())), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") long id, @Valid @RequestBody ClientApi clientApi) {

        Client client = clientService.findById(id);
        client.setFirstName(clientApi.getFirstName());
        client.setLastName(clientApi.getLastName());
        return new ResponseEntity<>(clientService.save(client), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClientNotFoundException() {
        return ResponseEntity.notFound().build();
    }

}
