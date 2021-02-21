package ar.com.plug.examen.domain.resource;

import ar.com.plug.examen.domain.dtos.ClienteDTO;
import ar.com.plug.examen.domain.endpoints.ClienteEndpoint;
import ar.com.plug.examen.domain.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= ClienteEndpoint.BASE)
public class ClienteResource implements ClienteEndpoint {

    @Autowired
    private IClienteService iClienteService;


    @Override
    @GetMapping(value = ClienteEndpoint.GET_ALL_CLIENTES,
    produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> getAll() {
        return iClienteService.getAll();
    }

    @Override
    @PostMapping(value = ClienteEndpoint.ADD_CLIENTE,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO save(@RequestBody ClienteDTO cliente) {
        return iClienteService.save(cliente);
    }

    @Override
    @PostMapping(value = ClienteEndpoint.DELETE_CLIENTE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody ClienteDTO cliente) {
        iClienteService.delete(cliente);
    }

    @Override
    @GetMapping(value = ClienteEndpoint.GET_CLIENTES_BY_NOMBRE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> getClienteByNombre(@RequestParam String nombre) {
        return iClienteService.getClienteByNombre(nombre);
    }
}
