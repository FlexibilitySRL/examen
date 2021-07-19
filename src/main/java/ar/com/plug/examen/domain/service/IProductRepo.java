package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.ProductDTO;

public interface IProductRepo {

	public ProductDTO findProductById(long id) throws ResourceNotFoundError;
	
	public List<ProductDTO> findAll();

	public ProductDTO save(ProductDTO product) throws BadRequestError;
	
	public ProductDTO update(ProductDTO product) throws ResourceNotFoundError, BadRequestError;

	public void delete(long id) throws ResourceNotFoundError;
	
}
