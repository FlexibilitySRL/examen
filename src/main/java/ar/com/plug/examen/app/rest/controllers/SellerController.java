package ar.com.plug.examen.app.rest.controllers;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.SellerDto;
import ar.com.plug.examen.app.rest.model.Seller;
import ar.com.plug.examen.app.rest.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService)
    {
        this.sellerService = sellerService;
    }


    @GetMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageDto<Seller> allSellers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size)
    {
        return this.sellerService.getAllSellers(page, size);
    }

    @GetMapping(value = "/seller/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Seller sellerById(
            @PathVariable Long id)
    {
        return this.sellerService.getSellerById(id);
    }

    @PostMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
    public int saveSeller(@RequestBody @Valid SellerDto sellerDto) throws ValidationException
    {
        return this.sellerService.saveSeller(sellerDto);
    }
    @DeleteMapping(value = "/seller/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteSeller(
            @PathVariable Long id) throws ValidationException
    {
        return this.sellerService.inactivateSeller(id);
    }

}
