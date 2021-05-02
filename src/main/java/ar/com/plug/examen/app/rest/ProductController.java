package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.Product;
import ar.com.plug.examen.domain.service.ProcessIdNameActiveModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ProductController.ROOT_PATH)
public class ProductController extends AbstractIdNameActiveModelController<Product> {

    //paths
    public static final String ROOT_PATH = "product";

    @Autowired
    public ProductController(ProcessIdNameActiveModelService<Product> processProductService) {
        super(processProductService);
    }

}