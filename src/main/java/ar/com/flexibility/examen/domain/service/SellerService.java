package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.exception.SellerException;

public interface SellerService {
    
    SellerApi add (SellerApi sellerApi) throws SellerException;
    SellerApi update (SellerApi sellerApi) throws SellerException;
    SellerApi remove (Long id) throws SellerException;
    SellerApi get (Long id ) throws SellerException;
    List <SellerApi> getAll (Long page, Long pageSize)throws SellerException;
}
