package ar.com.plug.examen.controller;

import ar.com.plug.examen.model.Clients;
import ar.com.plug.examen.service.ClientService;
import ar.com.plug.examen.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Contains methods for Clients.
 *
 * @author Camilo Villate
 */

@RestController
@RequestMapping(
        path = "/api/v1/clients"
)
public class ClientsController {

    private final ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }


    /**
     * Create a new Client in the database
     *
     * @param client - Json model of the client
     * @return - response
     */
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> insertNewClient(@RequestBody Clients client){
        int result = clientService.insertClient(client);
        return MessageResponse.getIntegerResponseEntity(result);
    }

    /**
     * Retrieve list of clients from database
     *
     * @return - response Json Array whit all clients
     */
    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<Clients> fetchUsers(){
        return clientService.getAllClients();
    }


    /**
     * Update  Client in the database
     *
     * @param client - Json model of the client
     * @return - response a json message
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateClient(@RequestBody Clients client){
        int result = clientService.updateClient(client);
        return MessageResponse.getIntegerResponseEntity(result);
    }

    /**
     * Delete Client from the database
     *
     * @param id - id to be delete
     * @return - response a json message
     */
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{id}"
    )
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        int result = clientService.deleteClient(id);
        return MessageResponse.getIntegerResponseEntity(result);
    }
}
