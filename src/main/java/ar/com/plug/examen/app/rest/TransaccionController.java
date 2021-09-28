package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.service.CompraService;
import ar.com.plug.examen.domain.service.TransaccionService;
import ar.com.plug.examen.dto.responses.TransaccionResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    TransaccionService transaccionService;

    @Autowired
    CompraService compraService;

    @GetMapping(value = "/iniciar", produces = "application/json")
    public ResponseEntity startTransaccion(@RequestParam Long clienteId){
        return ResponseEntity.ok(transaccionService.startTransaccion(clienteId));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity listTransacciones(){
        List<TransaccionResponse> transaccionesResponseList = new ArrayList<>();
        TransaccionResponse transaccionResponse = new TransaccionResponse();
        List<Transaccion> transacciones = transaccionService.listAllTransacciones();
        return ResponseEntity.ok(transacciones);
    }

    @PostMapping(value = "/finalizar", produces = "application/json")
    public ResponseEntity endTransaccion(@RequestParam Long id){
        return ResponseEntity.ok(transaccionService.endTransaccion(id));
    }
}
