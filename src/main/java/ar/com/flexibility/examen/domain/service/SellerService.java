package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Seller;

import java.util.List;

/**
 *  Seller Service contract. It exposes the methods that should be
 *  implemented by the more specific classes.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
public interface SellerService {

    Seller addSeller(Seller seller);
    Seller updateSeller(Long id, Seller seller);
    boolean deleteSeller(Long id);

    List<Seller> retrieveSellers();
    Seller retrieveSellerById(Long id);
}
