package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.SellerApi;

import java.util.List;

public interface SellerService {

    SellerApi create(SellerApi sellerApi);

    SellerApi retrieve(Long id);

    List<SellerApi> list();

    void remove(Long id);

    SellerApi update(Long id, SellerApi sellerApi);
}
