package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.SellerModel;
import ar.com.plug.examen.objects.JsonResponseTransaction;


public interface SellerService {

    JsonResponseTransaction addSeller(SellerModel sellerModel);
    JsonResponseTransaction deleteSeller(Long id);
    JsonResponseTransaction updateSeller(SellerModel sellerModel);
}
