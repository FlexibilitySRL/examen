package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Purcharse;
import ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping
    public List<Client> showClients(){
        return clientService.findAll();
    }

    @PostMapping
    public void addClient(@RequestBody Client client){
        clientService.addClient(client);
    }

    @PutMapping
    public void updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
    }

    @GetMapping(value = "{id}/transactions/")
    public List<Purcharse> getTransactions(@PathVariable Long id) {
        Client searchedClient = clientService.findById(id);

        return searchedClient.getPurcharses();
    }

}
