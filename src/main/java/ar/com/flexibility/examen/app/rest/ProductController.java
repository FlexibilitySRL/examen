package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.core.rest.AbstractController;
import ar.com.flexibility.examen.domain.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController extends AbstractController<Product> {

}
