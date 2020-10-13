package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Seller;

public interface ISellerService {

	public List<Seller> findAll();
	
	public Seller save(Seller cliente);
	
	public Seller findById(Long id);
	
	public void delete(Long id);
	
}
