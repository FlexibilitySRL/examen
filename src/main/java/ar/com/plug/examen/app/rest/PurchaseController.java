package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.PurchaseDTO;
import ar.com.plug.examen.domain.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @ApiOperation(value = "guarda un registro de una compra")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se agregan los valores correctamente"),
            @ApiResponse(code = 400, message = "Envio incorrecto  de información para la adiccion de los datos"),
            @ApiResponse(code = 500, message = "Ocurrio un error desconocido"),
    })
    @PostMapping(path = "/savePurchase", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        purchaseService.createPurchase(purchaseDTO);
    }

    @ApiOperation(value = "Obtiene todos las compras que se han realizado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Información consultada correctamente"),
            @ApiResponse(code = 400, message = "Envio incorrecto  de información para la consula de los datos"),
            @ApiResponse(code = 500, message = "Ocurrio un error desconocido"),
    })
    @GetMapping(value = "/listPurchases", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PurchaseDTO> getListPurchase() {
        return purchaseService.listPurchase();
    }

    @ApiOperation(value = "actuliza el estado  de una compra")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se modifico el estado  correctamente"),
            @ApiResponse(code = 400, message = "Envio incorrecto  de información para la actualizacion  de los datos"),
            @ApiResponse(code = 500, message = "Ocurrio un error desconocido"),
    })
    @PostMapping(path = "/approvePurchase/{idPurchase}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void approvePurchase(@PathVariable("idPurchase") Long id) {
        purchaseService.approvePurchase(id);
    }
}
