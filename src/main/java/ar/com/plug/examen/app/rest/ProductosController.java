package ar.com.plug.examen.app.rest;

import java.util.ArrayList;
import ar.com.plug.examen.domain.service.ProductoService;
import ar.com.plug.examen.domain.model.ProductosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/producto")
public class ProductosController {

    @Autowired
    ProductoService productoService;

    @GetMapping()
    public ArrayList<ProductosModel> obtenerProductos(){

        ArrayList<ProductosModel> resultado = productoService.findProductos();

        if(resultado != null){
            System.out.println("¡Consulta Eficiente!");
        }else{
            System.out.println("Hubo un error en la consulta");
        }
        return resultado;
    }

    @PostMapping()
    public ProductosModel registrarProducto(RequestBody ProductosModel productosModel){
        ProductosModel resultado = this.productoService.insertProduct(productosModel);
        if(resultado != null){
            System.out.println("¡Ha sido registrado correctamente!");
        }else{
            System.out.println("Los datos no fueron registrados");
        }
        return resultado;

    }

    @GetMapping(path = "/{id}")
    public Optional<ProductosModel> buscarProductoId(@PathVariable("id") Long id){
        ProductosModel resultado = this.productoService.findId(id);
        if(resultado != null){
            System.out.println("¡Consulta por Id Eficiente!");
        }else{
            System.out.println("Hubo un error en la consulta por Id");
        }
        return resultado;
    }

    @GetMapping(path = "/{query}")
    public ArrayList<ProductosModel> obtenerProductoMarca(@RequestParam("mark") String mark){
        ProductosModel resultado = productoService.findMark(mark);
         if(resultado != null){
            System.out.println("¡Consulta por Marca Eficiente!");
        }else{
            System.out.println("Hubo un error en la consulta por Marca");
        }
        return resultado;
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarProducto(@PathVariable("id") Long id){
        boolean resp = productoService.deleteProduct(id);

        if(resp){
            System.out.println("Eliminado el producto");
            return "Se ha eliminado el producto con id " + id;
        }else{
            System.out.println("No se pudo eliminar el producto");
            return "No se elimino el producto con id " + id;
        }
    }
}
