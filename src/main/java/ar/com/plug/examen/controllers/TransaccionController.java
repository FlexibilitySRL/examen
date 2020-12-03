package ar.com.plug.examen.controllers;
import ar.com.plug.examen.models.TransaccionModel;
import ar.com.plug.examen.services.TransaccionService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {
    @Autowired
    TransaccionService transaccionService;

    @GetMapping()
    public ArrayList<TransaccionModel> obtenerTransacciones() {
        return transaccionService.obtenerTransacciones();
    }

    @PostMapping()
    public TransaccionModel guardarTransaccion(@RequestBody TransaccionModel transaccion) {
        return this.transaccionService.guardarTransaccion(transaccion);
    }

    @PatchMapping(path = "/{id}")
    public TransaccionModel actualizarEstadoTransaccion(@RequestBody TransaccionModel transaccion) {
        return this.transaccionService.actualizarEstadoTransaccion(transaccion);
    }

    @GetMapping( path = "/{id}")
    public Optional<TransaccionModel> obtenerTransaccionPorId(@PathVariable("id") Long id) {
        return this.transaccionService.obtenerPorId(id);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.transaccionService.eliminarTransaccion(id);
        if (ok){
            return "Se eliminó la transacción con id " + id;
        }else{
            return "No pudo eliminar la transacción con id" + id;
        }
    }
}