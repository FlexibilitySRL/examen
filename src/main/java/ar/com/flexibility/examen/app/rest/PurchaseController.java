package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.PurchaseDto;
import ar.com.flexibility.examen.domain.model.filter.PurchaseFilter;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    /**
     * Para ingresar una compra los campos ids del dto deben completarse con
     * datos reales, de lo contrario si no existe siempre va a devolver null
     * la compra y nunca se va a guardar.
     * Por el momento el controller no dice que elemento no existe, el servicio si
     * @param purchase
     * @return en el caso exitoso la compra, en caso de faltar algo un null
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseDto create(@RequestBody PurchaseDto purchase) {
        return PurchaseDto.fromDomain(service.create(purchase));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<PurchaseDto> list() {
        final PurchaseFilter purchaseFilter = new PurchaseFilter(10L);
        return service.listWhere(purchaseFilter)
                .stream()
                .map(PurchaseDto::fromDomain).collect(Collectors.toList());
    }

    @RequestMapping(value = "/validate/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PurchaseDto validate(@PathVariable("id") long id) {
        return PurchaseDto.fromDomain(service.validate(id));
    }
}
