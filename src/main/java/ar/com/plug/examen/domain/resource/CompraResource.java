package ar.com.plug.examen.domain.resource;

import ar.com.plug.examen.domain.dtos.CompraDTO;
import ar.com.plug.examen.domain.endpoints.CompraEndpoint;
import ar.com.plug.examen.domain.services.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= CompraEndpoint.BASE)
public class CompraResource implements CompraEndpoint {

    @Autowired
    private ICompraService iCompraService;

    @Override
    @GetMapping(value = CompraEndpoint.GET_ALL_COMPRAS,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CompraDTO> getAll() {
        return iCompraService.getAll();
    }

    @Override
    @PostMapping(value = CompraEndpoint.ADD_COMPRA,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CompraDTO save(@RequestBody CompraDTO compra) throws Exception {
        return iCompraService.save(compra);
    }

    @Override
    @PostMapping(value = CompraEndpoint.DELETE_COMPRA,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody CompraDTO compra) {
        iCompraService.delete(compra);
    }
}
