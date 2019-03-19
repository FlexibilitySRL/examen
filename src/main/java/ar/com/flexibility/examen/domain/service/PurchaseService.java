package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.PurchaseDto;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.filter.PurchaseFilter;

import java.util.List;

public interface PurchaseService {

    Purchase create(PurchaseDto purchase);

    List<Purchase> listWhere(PurchaseFilter filter);

    Purchase validate(long id);
}
