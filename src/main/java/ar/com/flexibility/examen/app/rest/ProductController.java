package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.rest.mapper.ProductApiMapper;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public class ProductController extends GenericController<ProductApi, Product, ProductApiMapper, ProductService> {

    @Autowired
    public ProductController(ProductService service) {
        setService(service);
        setMapper(new ProductApiMapper());
    }
}