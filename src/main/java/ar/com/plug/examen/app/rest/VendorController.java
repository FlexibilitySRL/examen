package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.Vendor;
import ar.com.plug.examen.domain.service.impl.ProcessVendorModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = VendorController.ROOT_PATH)
public class VendorController
        extends AbstractBaseModelController<ProcessVendorModelServiceImpl, Vendor> {

    public static final String ROOT_PATH = "vendor";

    @Autowired
    public VendorController(ProcessVendorModelServiceImpl service) {
        super(service);
    }

}
