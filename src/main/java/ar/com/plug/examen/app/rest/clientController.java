package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.clientModel;
import ar.com.plug.examen.domain.service.clientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class clientController {

    @Autowired
    clientService clientService;

    @GetMapping("/getAll")
    @ApiOperation("Get All Clients")
    private ResponseEntity<List<clientModel>> getAllClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PostMapping("/save")
    @ApiOperation("Save a Client")
    public ResponseEntity<clientModel> saveClient(@RequestBody clientModel clientModel){

        try
        {
            clientModel clientSaved = clientService.saveClient(clientModel);
            return ResponseEntity.created(new URI("/clients/"+clientSaved.getId())).body(clientSaved);
        }
        catch (Exception e)
        {
            return new ResponseEntity<clientModel>(clientModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    @ApiOperation("Delete Client by Id")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        boolean ok = this.clientService.deleteClient(id);

        if (ok) return ResponseEntity.ok("Se eliminó el cliente con id " + id);
        else return ResponseEntity.badRequest().body("No se pudo eliminar el cliente con id" + id);
    }

}
