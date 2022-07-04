package ar.com.plug.examen.app.rest.controllers;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.ProductDto;
import ar.com.plug.examen.app.rest.model.Product;
import ar.com.plug.examen.app.rest.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.info(String.format("Request GET /products "));
        return this.productService.getAllProductsPageable(page, size);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product productById(
            @PathVariable Long id)
    {
        logger.info(String.format("Request GET /product/ %d ",id));
        return this.productService.getProductById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody @Valid ProductDto productDto) throws ValidationException
    {
        logger.info(String.format("Request POST /product"));
        return this.productService.saveProduct(productDto);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/bulk-product", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> bulkSaveProduct(@RequestBody @Valid List<ProductDto> productDto) throws ValidationException
    {
        logger.info(String.format("Request POST /bulk-product"));
        return this.productService.bulkSaveProduct(productDto);
    }

    @ResponseStatus(HttpStatus.OK)

    @DeleteMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteProduct(
            @PathVariable Long id) throws ValidationException
    {
        logger.info(String.format("Request DELETE /product/ %d",id));
        return this.productService.inactivateProduct(id);
    }
}
