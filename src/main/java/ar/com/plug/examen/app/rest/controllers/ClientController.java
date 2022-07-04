package ar.com.plug.examen.app.rest.controllers;

import ar.com.plug.examen.app.api.ClientDto;
import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.ProductDto;
import ar.com.plug.examen.app.rest.model.Client;
import ar.com.plug.examen.app.rest.model.Product;
import ar.com.plug.examen.app.rest.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.info(String.format("Request GET /clients "));

        return this.clientService.getAllClients(page, size);
    }

    @GetMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client clientById(
            @PathVariable Long id)
    {
        logger.info(String.format("Request GET /clients/ %d ",id));
        return this.clientService.getClientById(id);
    }
    @PostMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client saveClient(@RequestBody @Valid ClientDto clientDto) throws ValidationException
    {
        logger.info(String.format("Request POST /clients "));

        return this.clientService.saveClient(clientDto);
    }
    @DeleteMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteClient(
            @PathVariable Long id) throws ValidationException
    {
        logger.info(String.format("Request DELETE /clients/ %d ",id));
        return this.clientService.inactivateClient(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/bulk-client", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> bulkSaveClient(@RequestBody @Valid List<ClientDto> clientDtos) throws ValidationException
    {
        return this.clientService.bulkSaveClient(clientDtos);
    }
}
