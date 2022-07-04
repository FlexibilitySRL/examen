package ar.com.plug.examen.app.rest.controllers;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.PurchaseDto;
import ar.com.plug.examen.app.rest.model.Purchase;
import ar.com.plug.examen.app.rest.services.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
@RestController
public class PurchaseController
{
    private final PurchaseService purchaseService;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public PurchaseController(PurchaseService purchaseService)
    {
        this.purchaseService = purchaseService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/purchases", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageDto<Purchase> allPurchases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size)
    {
        logger.info(String.format("Request GET /purchases "));
        return this.purchaseService.getAllPurchasesPageable(page, size);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/purchases/approved", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageDto<Purchase> approvedPurchases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size)
    {
        logger.info(String.format("Request GET /purchases/approved "));
        return this.purchaseService.getApprovedPurchasesPageable(page, size);
    }

    @GetMapping(value = "/purchase/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Purchase purchaseById(
            @PathVariable Long id)
    {
        logger.info(String.format("Request GET /purchase/ %d ",id));
        return this.purchaseService.getPurchaseById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
    public Purchase createPurchase(@RequestBody @Valid PurchaseDto purchaseDto) throws ValidationException
    {
        logger.info(String.format("Request POST /purchase "));
        return this.purchaseService.savePurchase(purchaseDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/purchase/approve/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Purchase approvePurchase(
            @PathVariable Long id) throws ValidationException
    {
        logger.info(String.format("Request PUT /purchase/approve/ %d ",id));
        return this.purchaseService.approvePurchase(id);
    }
}
