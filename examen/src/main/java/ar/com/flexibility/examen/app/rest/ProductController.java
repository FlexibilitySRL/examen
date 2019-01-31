package ar.com.flexibility.examen.app.rest;

import com.example.core.model.Product;
import com.example.core.service.ProductService;
import com.example.core.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/producto")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private SaleService saleService;

    @RequestMapping(path = "/crear_producto", method = RequestMethod.POST)
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @RequestMapping(path = "/obtener_producto", method = RequestMethod.GET)
    public Product getProduct(@RequestParam(value = "idProduct") Long idProduct) {
        return productService.getProductById(idProduct);
    }

    @RequestMapping(path = "/lista_produtos", method = RequestMethod.GET)
    public List<Product> getProducts() {
        return productService.list();
    }

    @RequestMapping(path = "/actualizar_producto", method = RequestMethod.POST)
    public Product update(@RequestBody Product product) {
        return productService.update(product);
    }

    @RequestMapping(path = "/quitar_producto", method = RequestMethod.POST)
    public void delete(@RequestBody Long idProduct) {
        productService.delete(idProduct);
    }
}
