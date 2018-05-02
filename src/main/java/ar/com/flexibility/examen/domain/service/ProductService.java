package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.exception.ProductException;

public interface ProductService
{
    ProductApi add (ProductApi productApi) throws ProductException;
    ProductApi update (ProductApi productApi) throws ProductException;
    ProductApi remove (Long id) throws ProductException;
    ProductApi get (Long id ) throws ProductException;
    List <ProductApi> getAll (Long page, Long pageSize)throws ProductException;
}
