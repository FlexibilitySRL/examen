package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.service.CompraService;
import ar.com.plug.examen.domain.service.TransaccionService;
import ar.com.plug.examen.dto.requests.CompraRequest;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping(produces = "application/json")
    public ResponseEntity listCompras(@RequestParam(required = false) Long transaccionId) {
        if(transaccionId == null){
            return ResponseEntity.ok(compraService.listCompras());
        }
        return ResponseEntity.ok(compraService.listComprasByTransaccion(transaccionId));
    }

    @PostMapping(path = "/nueva", produces = "application/json")
    public ResponseEntity addProductoCarrito(@RequestBody CompraRequest compraRequest){
        return ResponseEntity.ok(compraService.addCompra(compraRequest));
    }

    @PostMapping(path = "/modificar", produces = "application/json")
    public ResponseEntity modifyProductoCarrito(@RequestBody CompraRequest compraRequest){
        return ResponseEntity.ok(compraService.updateCompra(compraRequest));
    }

    @DeleteMapping(path = "/borrar", produces = "application/json")
    public ResponseEntity deleteProductoCarrito(@RequestParam Long productoId, @RequestParam Long transaccionId){
        return ResponseEntity.ok(compraService.deleteCompraTransaccion(productoId, transaccionId));
    }
}
