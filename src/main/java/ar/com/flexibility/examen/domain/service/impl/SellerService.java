package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CRUD service for {@link Seller}
 */
@Service
@Transactional
public class SellerService extends AbstractCrudService<Seller, Long> {

	@Autowired
	public SellerService(SellerRepository sellerRepository) {
		this.repository = sellerRepository;
	}
}
