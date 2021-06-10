package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.ProductModel;
import ar.com.plug.examen.domain.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AGB
 */
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ProductModel addProduct(@RequestBody ProductModel product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/addProducts")
    public List<ProductModel> addProducts(@RequestBody List<ProductModel> products) {
        return productService.saveProducts(products);
    }

    @PostMapping("/listProducts")
    public List<ProductModel> listProducts() {
        return productService.getProducts();
    }

    @PostMapping("/product/{id}")
    public ProductModel findProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/product/{name}")
    public ProductModel findProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @PostMapping("/updateProd")
    public ProductModel updateProduct(@RequestBody ProductModel product) {
        return productService.updateProduct(product);
    }

    @PostMapping("/deleteProd/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

}
