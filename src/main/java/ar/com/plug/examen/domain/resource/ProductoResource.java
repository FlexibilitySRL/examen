package ar.com.plug.examen.domain.resource;

import ar.com.plug.examen.domain.dtos.ProductoDTO;
import ar.com.plug.examen.domain.endpoints.ProductoEndpoint;
import ar.com.plug.examen.domain.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= ProductoEndpoint.BASE)
public class ProductoResource implements ProductoEndpoint {

    @Autowired
    private IProductoService iProductoService;

    @Override
    @GetMapping(value = ProductoEndpoint.GET_ALL_PRODUCTOS,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoDTO> getAll() {
        return iProductoService.getAll();
    }

    @Override
    @PostMapping(value = ProductoEndpoint.ADD_PRODUCTO,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductoDTO save(@RequestBody ProductoDTO producto) {
        return iProductoService.save(producto);
    }

    @Override
    @PostMapping(value = ProductoEndpoint.DELETE_PRODUCTO,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody ProductoDTO producto) {
        iProductoService.delete(producto);
    }
}
