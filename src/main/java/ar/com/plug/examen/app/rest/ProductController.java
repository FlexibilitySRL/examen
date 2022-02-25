package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "guarda un registro de un producto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se agregan los valores correctamente"),
            @ApiResponse(code = 400, message = "Envio incorrecto  de información para la adiccion de los datos"),
            @ApiResponse(code = 500, message = "Ocurrio un error desconocido"),
    })
    @PostMapping(path = "/saveProduct", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void createProduct(@RequestBody ProductDTO productDTO) {
        productService.createProduct(productDTO);
    }

    @ApiOperation(value = "Eliminar un registro de un producto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se elimaron los valores correctamente"),
            @ApiResponse(code = 400, message = "Envio incorrecto  de información para la elimicación de los datos"),
            @ApiResponse(code = 500, message = "Ocurrio un error desconocido"),
    })
    @DeleteMapping(path = "/removeProduct/{idProduct}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@PathVariable("idProduct") Long id) {
        productService.deleteProduct(id);
    }

    @ApiOperation(value = "guarda un registro de un producto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se agregan los valores correctamente"),
            @ApiResponse(code = 400, message = "Envio incorrecto  de información para la edicción de los datos"),
            @ApiResponse(code = 500, message = "Ocurrio un error desconocido"),
    })
    @PostMapping(path = "/editProduct/{idProduct}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void modifyProduct(@PathVariable("idProduct") Long id, @RequestBody ProductDTO productDTO) {
        productService.editProduct(id, productDTO);
    }

}
