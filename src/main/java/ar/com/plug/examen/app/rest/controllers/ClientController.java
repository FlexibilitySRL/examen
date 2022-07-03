package ar.com.plug.examen.app.rest.controllers;

import ar.com.plug.examen.app.api.ClientDto;
import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.rest.model.Client;
import ar.com.plug.examen.app.rest.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService)
    {
        this.clientService = clientService;
    }


    @GetMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageDto<Client> allClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size)
    {
        return this.clientService.getAllClients(page, size);
    }

    @GetMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client clientById(
            @PathVariable Long id)
    {
        return this.clientService.getClientById(id);
    }
    @PostMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client saveClient(@RequestBody @Valid ClientDto clientDto) throws ValidationException
    {
        return this.clientService.saveClient(clientDto);
    }
    @DeleteMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteClient(
            @PathVariable Long id) throws ValidationException
    {
        return this.clientService.inactivateClient(id);
    }

}
