package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ClientDto;
import ar.com.flexibility.examen.app.api.GenericResponse;
import ar.com.flexibility.examen.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto create(@RequestBody ClientDto client) {
        return new ClientDto(service.createOrUpdate(client.toDomain()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public GenericResponse delete(@PathVariable("id") long id) {
        return new GenericResponse(service.delete(id));
    }

}
