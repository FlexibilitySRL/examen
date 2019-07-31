package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purcharse;

import java.util.List;

public interface PurcharseService {

    Purcharse addPurcharse(Purcharse purcharse);

    Purcharse addProduct(Purcharse purcharse, Product product);

    Purcharse updatePurcharse(Purcharse purcharse);

    Purcharse findById(Long id);

    void deletePurcharse(Long id);

    List<Purcharse> findAll();
}
