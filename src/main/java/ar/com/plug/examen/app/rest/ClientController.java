package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.impl.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll(){
        List<Client> sales = this.clientService.getAll();
        return ResponseEntity.ok(sales);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id){
        Client client = this.clientService.getById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> insert(@RequestBody Client client){
        Client newClient = this.clientService.saveOrUpdate(client);
        return ResponseEntity.ok(newClient);
    }

    @PatchMapping
    public ResponseEntity<Client> update(@RequestBody Client client){
        Client newClient = this.clientService.saveOrUpdate(client);
        return ResponseEntity.ok(newClient);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        this.clientService.delete(id);
    }

}
