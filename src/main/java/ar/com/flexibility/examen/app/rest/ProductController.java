package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.GenericResponse;
import ar.com.flexibility.examen.app.api.ProductDto;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody ProductDto product) {
        return ProductDto.fromDomain(service.create(product.toDomain()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ProductDto findById(@PathVariable("id") long id) {
        return ProductDto.fromDomain(service.findById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public GenericResponse delete(@PathVariable("id") long id) {
        return new GenericResponse(service.delete(id));
    }
}
