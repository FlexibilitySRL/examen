/**
 * 
 */
package ar.com.flexibility.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.SellerService;

/**
 * @author ro
 *
 */

@Service
@Transactional(readOnly=true)
public class SellerServiceImpl implements SellerService {
	@Autowired
	private SellerRepository sellerRepository;
	
	@Override
	public Seller findById(Long idSeller) {
		return sellerRepository.findOne(idSeller);
	}

}
