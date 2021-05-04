package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.Product;
import ar.com.plug.examen.domain.service.ProcessBaseModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ProductController.ROOT_PATH)
public class ProductController
        extends AbstractBaseModelController<ProcessBaseModelService<Product>, Product> {

    //paths
    public static final String ROOT_PATH = "product";

    @Autowired
    public ProductController(ProcessBaseModelService<Product> processProductService) {
        super(processProductService);
    }

}