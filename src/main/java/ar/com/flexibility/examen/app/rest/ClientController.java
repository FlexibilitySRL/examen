package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.rest.mapper.ClientApiMapper;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for {@link Client} CRUD operations.
 */
@RequestMapping(path = "/client")
@RestController
@Api(description = "Controller for Client CRUD operations.")
public class ClientController extends GenericController<ClientApi, Client, ClientApiMapper, ClientService> {

    @Autowired
    public ClientController(ClientService service) {
        setService(service);
        setMapper(new ClientApiMapper());
    }
}
