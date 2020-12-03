package ar.com.plug.examen.controllers;
import ar.com.plug.examen.models.ClienteModel;
import ar.com.plug.examen.services.ClienteService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping()
    public ArrayList<ClienteModel> obtenerClientes() {
        return clienteService.obtenerClientes();
    }

    @PostMapping()
    public ClienteModel guardarCliente(@RequestBody ClienteModel cliente) {
        return this.clienteService.guardarCliente(cliente);
    }

    @GetMapping( path = "/{id}")
    public Optional<ClienteModel> obtenerClientePorId(@PathVariable("id") Long id) {
        return this.clienteService.obtenerPorId(id);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.clienteService.eliminarCliente(id);
        if (ok){
            return "Se eliminó el cliente con id " + id;
        }else{
            return "No pudo eliminar el cliente con id" + id;
        }
    }
}