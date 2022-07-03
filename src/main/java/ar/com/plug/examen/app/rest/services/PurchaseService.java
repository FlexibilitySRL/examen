package ar.com.plug.examen.app.rest.services;


import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.PurchaseDto;
import ar.com.plug.examen.app.rest.model.Purchase;

import javax.xml.bind.ValidationException;

public interface PurchaseService
{

    PageDto<Purchase> getApprovedPurchasesPageable(int pageNumber, int pageSize);

    PageDto<Purchase> getAllPurchasesPageable(int pageNumber, int pageSize);

    Purchase getPurchaseById(Long id);


    Purchase savePurchase(PurchaseDto purchaseDto) throws ValidationException;


    Purchase approvePurchase(Long id) throws ValidationException;

}
