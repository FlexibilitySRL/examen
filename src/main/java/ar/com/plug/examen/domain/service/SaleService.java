package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.Product;
import ar.com.plug.examen.domain.model.SaleModel;
import ar.com.plug.examen.objects.JsonRequestSale;
import ar.com.plug.examen.objects.JsonResponseTransaction;
import ar.com.plug.examen.objects.StatusTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface SaleService {
    JsonResponseTransaction createSale(JsonRequestSale jsonRequestSale);
    SaleModel addSale(SaleModel saleModel);
    StatusTransaction deleteSale(Long id);
    SaleModel updateSale(SaleModel saleModel);

    List<SaleModel> getSaleTrxAll();
    List<Product> getProductsAvailableForSale(JsonRequestSale jsonRequestSale);
    JsonResponseTransaction confirmSale(JsonRequestSale jsonRequestSale) throws JsonProcessingException;
    boolean setPaymentType(JsonRequestSale jsonRequestSale);

}
