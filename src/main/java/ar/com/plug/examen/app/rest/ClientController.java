package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.domain.exception.ClientNotFoundException;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.validators.Validator;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    private Validator validator;


    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody ClientDTO clientDTO)
    {
        validator.validateClientDTO(clientDTO, Boolean.FALSE);
        return new ResponseEntity<>(clientService.save(clientDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ClientDTO>> getAllClients()
    {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "API to get client by id", consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClientById(@PathVariable Long id)
    {
        return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO) {

        validator.validateClientDTO(clientDTO, Boolean.TRUE);
        return new ResponseEntity<>(this.clientService.update(clientDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
