package ar.com.plug.examen.domain.resource;

import ar.com.plug.examen.domain.dtos.VendedorDTO;
import ar.com.plug.examen.domain.endpoints.VendedorEndpoint;
import ar.com.plug.examen.domain.services.IVendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= VendedorEndpoint.BASE)
public class VendedorResource implements VendedorEndpoint {

    @Autowired
    private IVendedorService iVendedorService;

    @Override
    @GetMapping(value = VendedorEndpoint.GET_ALL_VENDEDORES,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<VendedorDTO> getAll() {
        return iVendedorService.getAll();
    }

    @Override
    @PostMapping(value = VendedorEndpoint.ADD_VENDEDOR,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public VendedorDTO save(@RequestBody VendedorDTO vendedor) {
        return iVendedorService.save(vendedor);
    }

    @Override
    @PostMapping(value = VendedorEndpoint.DELETE_VENDEDOR,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody VendedorDTO vendedor) {
        iVendedorService.delete(vendedor);
    }
}
