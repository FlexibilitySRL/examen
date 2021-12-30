package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.ProductoDto;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.service.ProductoService;
import ar.com.plug.examen.exception.MicroserviceErrorException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Log4j2
@RestController
@Api(value = "ProductoController", tags = "Operaciones pertenecientes a producto.")
@RequestMapping(path = "/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @Autowired
    ModelMapper modelMapper;

    @ApiOperation(value = "Buscar un producto por un ID", response = ProductoDto.class)
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> show(@PathVariable Integer id) {
        Producto producto = productoService.findProductoById(id);
        if (Objects.nonNull(producto)) {
            ProductoDto productoResponse = modelMapper.map(producto, ProductoDto.class);
            return new ResponseEntity<>(productoResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Eliminar un producto")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        try {
            productoService.deleteProductoById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Agregar un producto")
    @PostMapping("/")
    public ResponseEntity<ProductoDto> crear(@RequestBody ProductoDto productoDto) {
        try {
            Producto productoRequest = modelMapper.map(productoDto, Producto.class);
            Producto p = productoService.saveProducto(productoRequest);

            ProductoDto productoResponse = modelMapper.map(p, ProductoDto.class);
            return new ResponseEntity<>(productoResponse, HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Actualizar un producto")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> update(
            @PathVariable("id") Integer id,
            @RequestBody ProductoDto productoDto) throws MicroserviceErrorException {

        Producto productoRequest = modelMapper.map(productoDto, Producto.class);
        Producto producto = productoService.updateProducto(productoRequest, id);

        ProductoDto productoResponse = modelMapper.map(producto, ProductoDto.class);
        if (Objects.nonNull(productoResponse)) {
            return new ResponseEntity<>(productoResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
