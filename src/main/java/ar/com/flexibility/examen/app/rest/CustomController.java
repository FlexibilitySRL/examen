package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.MessageApi;
import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.ProcessMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*Api rest 
 * 4 tablas cliente, productos,compras y vendedores
 * Miguel Mavo.
 * puntos a destacar de la prueba:
 * -realiza correctamente las operaciones CRUD excepto la de compras por problemas
 * de integridad referencial. por gestor de bd si lo realiza.
 * -faltante:pruebas unitarias
 * -no pude lograr solventarlo por el tiempo.
 * -los logs lo implemente a nivel de controlador 
 * 
 * */
@RestController
@RequestMapping(path = "/custom")
public class CustomController {

    @Autowired
    private ProcessMessageService messageService;

    @PostMapping("/echo")
    public ResponseEntity<?> echo(@RequestBody MessageApi message)
    {
        return new ResponseEntity<Message>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
    }
  
}
