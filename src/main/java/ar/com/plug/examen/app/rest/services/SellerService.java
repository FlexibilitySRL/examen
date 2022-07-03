package ar.com.plug.examen.app.rest.services;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.SellerDto;
import ar.com.plug.examen.app.rest.model.Seller;

import javax.xml.bind.ValidationException;

public interface SellerService {

    PageDto<Seller> getAllSellers(int pageNumber, int pageSize);

    Seller getSellerById(Long id);

    int saveSeller(SellerDto sellerDto) throws ValidationException;

    Boolean inactivateSeller(Long id) throws ValidationException;

}
