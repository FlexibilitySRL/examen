package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.app.rest.mapper.SellerApiMapper;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.SellerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for {@link Seller} CRUD operations.
 */
@RequestMapping(path = "/seller")
@RestController
@Api(description = "Controller for Seller CRUD operations.")
public class SellerController extends GenericController<SellerApi, Seller, SellerApiMapper, SellerService> {

    @Autowired
    public SellerController(SellerService service) {
        setService(service);
        setMapper(new SellerApiMapper());
    }
}
