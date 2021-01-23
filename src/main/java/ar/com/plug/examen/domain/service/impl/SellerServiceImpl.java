package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.exception.NotSellerFoundException;
import ar.com.plug.examen.repository.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerRepository repository;

	@Override
	public List<Seller> getAllSeller() {
		List<Seller> result = (List<Seller>) repository.findAll();
		return result;
	}

	@Override
	public Seller getSellerById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotSellerFoundException(id));
	}

	@Override
	public void deleteSeller(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Seller saveOrUpdate(Seller seller) {
		return repository.save(seller);
	}
	
}
