package ar.com.flexibility.examen.app.rest;

import com.example.core.model.Client;
import com.example.core.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cliente")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @RequestMapping(path = "/detalle_cuenta")
    public Client getClient(@RequestParam("username") String username) {
        return clientService.information(username);
    }

    @RequestMapping(path = "/actualizar_cuenta", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody Client client) {
        clientService.update(client);
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    @RequestMapping(path = "/registrar", method = RequestMethod.POST)
    public ResponseEntity signup(@RequestBody Client client) {
        clientService.create(client);
        return new ResponseEntity("Ok", HttpStatus.OK);
    }
}
