package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.service.ProductoService;
import ar.com.plug.examen.dto.requests.ProductoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping(path = "/new", produces = "application/json")
    public ResponseEntity addProducto(@RequestBody ProductoRequest productoRequest) {
        return ResponseEntity.ok(productoService.addProducto(productoRequest));
    }

    @PostMapping(path = "/update", produces = "application/json")
    public ResponseEntity modifyProducto(@RequestBody ProductoRequest productoRequest, @RequestParam Long id) {
        return ResponseEntity.ok(productoService.updateProducto(productoRequest, id));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity listProductos(){
        return ResponseEntity.ok(productoService.findAllProductos());
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity findProductoById(@PathVariable Long id){
        return ResponseEntity.ok(productoService.findById(id));
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity deleteProductoById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.deleteProducto(id));
    }
}
