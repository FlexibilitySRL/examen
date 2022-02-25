package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.service.SellerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @ApiOperation(value = "guarda un registro de un vendedor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se agregan los valores correctamente"),
            @ApiResponse(code = 400, message = "Envio incorrecto  de información para la adiccion de los datos"),
            @ApiResponse(code = 500, message = "Ocurrio un error desconocido"),
    })
    @PostMapping(path = "/saveSeller", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void createSeller(@RequestBody SellerDTO sellerDTO) {
        sellerService.createSeller(sellerDTO);
    }

    @ApiOperation(value = "Eliminar un registro de un vendedor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se elimaron los valores correctamente"),
            @ApiResponse(code = 400, message = "Envio incorrecto  de información para la elimicación de los datos"),
            @ApiResponse(code = 500, message = "Ocurrio un error desconocido"),
    })
    @DeleteMapping(path = "/removeSeller/{idSeller}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSeller(@PathVariable("idSeller") Long id) {
        sellerService.deleteSeller(id);
    }

    @ApiOperation(value = "guarda un registro de un cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se agregan los valores correctamente"),
            @ApiResponse(code = 400, message = "Envio incorrecto  de información para la edicción de los datos"),
            @ApiResponse(code = 500, message = "Ocurrio un error desconocido"),
    })
    @PostMapping(path = "/editSeller/{idSeller}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void modifySeller(@PathVariable("idSeller") Long id, @RequestBody SellerDTO sellerDTO) {
        sellerService.editSeller(id, sellerDTO);
    }
}
