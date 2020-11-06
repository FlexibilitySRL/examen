package ar.com.plug.examen.controller;

import ar.com.plug.examen.model.Clients;
import ar.com.plug.examen.service.ClientService;
import ar.com.plug.examen.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> insertNewClient(@RequestBody Clients client){
        int result = clientService.insertClient(client);
        return getIntegerResponseEntity(result);
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<Clients> fetchUsers(){
        return clientService.getAllClients();
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateClient(@RequestBody Clients client){
        int result = clientService.updateClient(client);
        return getIntegerResponseEntity(result);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{id}"
    )
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        int result = clientService.deleteClient(id);
        return getIntegerResponseEntity(result);
    }


    private ResponseEntity<?> getIntegerResponseEntity(int result) {
        if(result ==1){
            return  ResponseEntity.ok().body(new MessageResponse("Proceso correcto"));
        }
        return ResponseEntity.badRequest().build();
    }

}
