package ar.com.plug.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.rest.paths.Paths;
import ar.com.plug.examen.domain.Exeptions.BadRequestException;
import ar.com.plug.examen.domain.Exeptions.NotFoundException;
import ar.com.plug.examen.domain.service.ClientService;

@RestController
@RequestMapping(path= Paths.CLIENT)
public class ClientController {
	@Autowired
    private ClientService clientService;

    @GetMapping()
    public ResponseEntity<List<ClientApi>> listClients() {
        return new ResponseEntity<>(clientService.listAll(), HttpStatus.OK);
    }

    @GetMapping(Paths.FIND_BY_ID)
    public ResponseEntity<ClientApi> findById(@PathVariable long id) throws NotFoundException {
    	return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @GetMapping(Paths.FIND_BY_NAME)
    public ResponseEntity<List<ClientApi>> findByName(@PathVariable String name) {
    	return new ResponseEntity<>(clientService.findByName(name), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ClientApi> save(@RequestBody ClientApi client) throws BadRequestException {
    	return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

	@DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) throws NotFoundException {
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientApi> update(@RequestBody ClientApi clientApi) throws NotFoundException, BadRequestException {
    	return new ResponseEntity<>(clientService.update(clientApi), HttpStatus.ACCEPTED);
    }

}
