package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
import ar.com.flexibility.examen.app.rest.mapper.ProductApiMapper;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("{id}")
    public ProductApi find(@Valid @PathVariable long id) throws EntityNotFoundException {
        return ProductApiMapper
                .buildProductApi(productService.findOne(id));
    }

    @GetMapping("/")
    public List<ProductApi> list() {
        return productService
                .listAll()
                .stream()
                .map(ProductApiMapper::buildProductApi)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductApi create(@Valid @RequestBody ProductApi productApi) throws ConstraintsViolationException {
        return ProductApiMapper
                .buildProductApi(
                        productService
                                .create(
                                        ProductApiMapper.buildProduct(productApi)));
    }

    @PutMapping
    public ProductApi update(@Valid @RequestBody ProductApi productApi) throws EntityNotFoundException {
        return ProductApiMapper
                .buildProductApi(
                        productService
                                .save(
                                        ProductApiMapper.buildProduct(productApi)));
    }

    @DeleteMapping("{id}")
    public ProductApi delete(@Valid @PathVariable Long id) throws ConstraintsViolationException, EntityNotFoundException {
        return ProductApiMapper
                .buildProductApi(
                        productService
                                .delete(id));
    }
}