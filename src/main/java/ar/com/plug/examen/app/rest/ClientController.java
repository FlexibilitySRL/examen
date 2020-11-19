package ar.com.plug.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.service.ClientService;

@RestController
@RequestMapping(path = Paths.CLIENT)
public class ClientController {

	@Autowired
    private ClientService clientService;

    @GetMapping()
    public List<ClientApi> listClients() {
        return clientService.listAll();
    }

    @GetMapping(Paths.FIND_BY_ID)
    public ClientApi findById(@PathVariable Long id) throws NotFoundException {
    	return clientService.findById(id);
    }

    @GetMapping(Paths.FIND_BY_NAME)
    public List<ClientApi> findByName(@PathVariable String name) throws NotFoundException {
    	return clientService.findByName(name);
    }
    
    @PostMapping()
    public ClientApi save(@RequestBody ClientApi client) throws BadRequestException {
    	return clientService.save(client);
    }

	@DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws NotFoundException {
        clientService.deleteById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientApi update(@RequestBody ClientApi clientApi) throws NotFoundException, BadRequestException {
    	return clientService.update(clientApi);
    }
}
