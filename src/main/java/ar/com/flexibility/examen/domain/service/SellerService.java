/**
 * 
 */
package ar.com.flexibility.examen.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;

/**
 * @author rosalizaracho
 *
 */
@Service
@Transactional(readOnly=true)
public class SellerService {
	
	@Autowired
	private SellerRepository sellerRepository;
	
	public Seller findById(Long idSeller) {
		return sellerRepository.findOne(idSeller);
	}

}
