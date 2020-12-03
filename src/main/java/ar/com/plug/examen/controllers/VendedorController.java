package ar.com.plug.examen.controllers;
import ar.com.plug.examen.models.VendedorModel;
import ar.com.plug.examen.services.VendedorService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {
    @Autowired
    VendedorService vendedorService;

    @GetMapping()
    public ArrayList<VendedorModel> obtenerVendedores() {
        return vendedorService.obtenerVendedores();
    }

    @PostMapping()
    public VendedorModel guardarVendedor(@RequestBody VendedorModel vendedor) {
        return this.vendedorService.guardarVendedor(vendedor);
    }

    @GetMapping( path = "/{id}")
    public Optional<VendedorModel> obtenerVendedorPorId(@PathVariable("id") Long id) {
        return this.vendedorService.obtenerPorId(id);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.vendedorService.eliminarVendedor(id);
        if (ok){
            return "Se eliminó el cliente con id " + id;
        }else{
            return "No pudo eliminar el cliente con id" + id;
        }
    }
}