package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.api.PurchaseApi;
import ar.com.flexibility.examen.exception.PurchaseException;

public interface PurchaseService
{
    PurchaseApi add (PurchaseApi purchaseApi) throws PurchaseException;
    PurchaseApi update (PurchaseApi purchaseApi) throws PurchaseException;
    PurchaseApi remove (Long id) throws PurchaseException;
    PurchaseApi get (Long id ) throws PurchaseException;
    List <PurchaseApi> getAll (Long page, Long pageSize)throws PurchaseException;
}
