package ar.com.plug.examen.app.rest;

import java.util.ArrayList;
import ar.com.plug.examen.domain.service.TransaccionService;
import ar.com.plug.examen.domain.model.TransaccionModel;
import ar.com.plug.examen.domain.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    @Autowired
    TransaccionService transaccionService;

    @GetMapping(path = "/{query}")
    public ArrayList<TransaccionModel> obtenerTransaccionFecha(@RequestParam("datePurch") Date datePurch){
        TransaccionModel resultado = transaccionService.findTransactionsDate(datePurch);
         if(resultado != null){
            System.out.println("¡Consulta por Fecha de Transacciones Eficiente!");
        }else{
            System.out.println("Hubo un error en la consulta de transacciones por Fecha");
        }
        return resultado;
    }

    @PostMapping()
    public ArrayList<TransaccionModel> obtenerTransaccionCliente(RequestBody ClienteModel client){
        TransaccionModel resultado = transaccionService.findByTransactionClient(client);
         if(resultado != null){
            System.out.println("¡Consulta de Transacciones por Cliente ha sido Eficiente!");
        }else{
            System.out.println("Hubo un error en la consulta de transacciones por Cliente");
        }
        return resultado;
    }

}