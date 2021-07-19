package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.domain.model.SellerDTO;

public interface ISellerRepo {
	
	public SellerDTO findSellerByID(long id) throws ResourceNotFoundError;
	
	public List<SellerDTO> findAll();

	public SellerDTO save(SellerDTO seller) throws BadRequestError;
	
	public SellerDTO update(SellerDTO seller) throws ResourceNotFoundError, BadRequestError;

	public void delete(long id) throws ResourceNotFoundError;

}
