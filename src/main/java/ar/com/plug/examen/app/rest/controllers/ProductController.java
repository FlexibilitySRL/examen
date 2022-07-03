package ar.com.plug.examen.app.rest.controllers;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.ProductDto;
import ar.com.plug.examen.app.rest.model.Product;
import ar.com.plug.examen.app.rest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
public class ProductController
{
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageDto<Product> allProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size)
    {
        return this.productService.getAllProductsPageable(page, size);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product productById(
            @PathVariable Long id)
    {
        return this.productService.getProductById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody @Valid ProductDto productDto) throws ValidationException
    {
        return this.productService.saveProduct(productDto);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/bulk-product", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> bulkSaveProduct(@RequestBody @Valid List<ProductDto> productDto) throws ValidationException
    {
        return this.productService.bulkSaveProduct(productDto);
    }

    @ResponseStatus(HttpStatus.OK)

    @DeleteMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteProduct(
            @PathVariable Long id) throws ValidationException
    {
        return this.productService.inactivateProduct(id);
    }
}
