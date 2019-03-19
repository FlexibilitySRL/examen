package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.GenericResponse;
import ar.com.flexibility.examen.app.api.SellerDto;
import ar.com.flexibility.examen.domain.service.SellerService;
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
@RequestMapping(path = "/seller")
public class SellerController {

    @Autowired
    private SellerService service;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public SellerDto create(@RequestBody SellerDto seller) {
        return new SellerDto(service.createOrUpdate(seller.toDomain()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public GenericResponse delete(@PathVariable("id") long id) {
        return new GenericResponse(service.delete(id));
    }
}
