package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Purchase;

import java.util.List;

public interface PurchaseService {

    /**
     * Retorna una compra en base al id
     * @param id
     * @return Purchase
     */
    public Purchase findById(Long id);

    /**
     * Borra una compra en base al id
     * @param id
     */
    void deleteById(Long id);

    /**
     * Crea una compra
     * @param purchase a crear
     * @return compra creada
     */
    Purchase create(Purchase purchase);

    /**
     * Actualiza el estado de la compra
     * @param purchase a actualizar
     * @return Purchase actualizado
     */
    Purchase update(Purchase purchase);

    /**
     * Retorna todos las compras
     * @return List<Purchase>
     */
    List<Purchase> findAll();
}
