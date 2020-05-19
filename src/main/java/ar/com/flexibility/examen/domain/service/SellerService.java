package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.exception.GenericException;

public interface SellerService {

	public Seller create(Seller seller) throws GenericException;

	public Seller getById(Long id) throws GenericException;

	public List<Seller> getAll();

	public Seller update(Seller seller) throws GenericException;

	public void delete(Long id) throws GenericException;

}
