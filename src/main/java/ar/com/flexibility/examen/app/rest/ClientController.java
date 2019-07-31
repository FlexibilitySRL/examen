package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.model.Purcharse;
import ar.com.flexibility.examen.domain.service.impl.AuthorizationServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.PurcharseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private PurcharseServiceImpl purcharseService;
    @Autowired
    private AuthorizationServiceImpl authorizationService;

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

    @PutMapping(value = "/{idCliente}/transactions/{idPurcharse}/authorize")
    public void authorizePurcharse(@PathVariable("idCliente") Long idClient, @PathVariable("idPurcharse") Long idPurcharse) {
        Purcharse searchedPurcharse = purcharseService.findById(idPurcharse);

        Transaction authorizedTransaction = authorizationService.authorize(searchedPurcharse);
        searchedPurcharse.addTransaction(authorizedTransaction);

        purcharseService.updatePurcharse(searchedPurcharse);

    }
}
