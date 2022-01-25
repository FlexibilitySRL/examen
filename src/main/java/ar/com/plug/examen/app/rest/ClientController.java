package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/clients")
public class ClientController
{

    @Autowired
    private ClientService clientService;


    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveClient(@RequestBody ClientDTO clientDTO)
    {
        return new ResponseEntity<>(clientService.save(clientDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ClientDTO>> getAllClients()
    {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }
}
