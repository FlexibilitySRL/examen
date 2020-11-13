package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exceptions.PurchaseDoesNotExistException;
import ar.com.plug.examen.domain.model.Purchase;

import java.util.List;

public interface IPurchaseService {

    /**
     * List all the products on the DB.
     * @return List<Product>
     */
    List<Purchase> findAll();

    /**
     * Save the product passed by param
     * @param aPurchase
     * @return aProduct
     */
    Purchase savePurchase(Purchase aPurchase);

    /**
     * Find a purchase by id
     * @param id
     * @return aPurchase
     */
    Purchase findById(Long id) throws PurchaseDoesNotExistException;

    /**
     * Delete the purchase
     * @param id
     */
    void deletePurchase(Long id);

    /**
     * Method to update a purchase
     * @param aPurchase
     * @return
     */
    Purchase updatePurchase(Purchase aPurchase) throws PurchaseDoesNotExistException;
}
