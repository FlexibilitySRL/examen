package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Seller;

public interface SellerService {

    Seller createOrUpdate(Seller seller);

    Boolean delete(long id);
}
