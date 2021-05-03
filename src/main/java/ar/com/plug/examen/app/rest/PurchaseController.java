package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.Purchase;
import ar.com.plug.examen.domain.service.ProcessIdModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = PurchaseController.ROOT_PATH)
public class PurchaseController
        extends AbstractIdModelController<ProcessIdModelService<Purchase>, Purchase> {

    //paths
    public static final String ROOT_PATH = "purchase";

    @Autowired
    public PurchaseController(ProcessIdModelService<Purchase> service) {
        super(service);
    }

}