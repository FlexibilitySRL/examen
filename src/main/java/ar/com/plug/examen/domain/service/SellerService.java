package ar.com.plug.examen.domain.service;

import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.SellerDto;
import ar.com.plug.examen.domain.model.Seller;

public interface SellerService
{
	PageDto<Seller> getActiveSellersPageable(int pageNumber, int pageSize);

	PageDto<Seller> getAllSellersPageable(int pageNumber, int pageSize);

	Seller getSellerById(Long id);

	Seller getSellerByCode(String code);

	Seller saveSeller(SellerDto sellerDto) throws ValidationException;

	Seller updateSeller(Long id, SellerDto sellerDto) throws ValidationException;

	Seller inactivateSeller(Long id) throws ValidationException;

	Long deleteSeller(Long id) throws ValidationException;
}
