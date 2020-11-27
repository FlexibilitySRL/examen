package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.SellerService;


/**
 * Implementation of the SellerService that uses a CrudRepository
 *
 * @author julimanfre@hotmail.com
 */

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
    SellerRepository sellerRepository;
    
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SellerServiceImpl.class);

	@Override
	public Seller create(Seller seller) {
		
		LOGGER.info("Creating Seller....");
 
		
		Seller sellerCreated = sellerRepository.save(seller);
		
		if( sellerCreated==null){

			LOGGER.error("Seller not Created....");

		}else{
			
			LOGGER.info("Seller Created Succesfully....");
		}
		
		return sellerCreated;
		
		
 	}

	@Override
	public Seller update(Long id, Seller seller) {
		
		LOGGER.info("Updating Seller....");

		Seller sellerUpdated = sellerRepository.save(seller);
		
		if( sellerUpdated==null){

			LOGGER.error("Seller not Created....");

		}else{
			
			LOGGER.info("Seller Created Succesfully....");
		}
		
		return sellerUpdated;
	}

	@Override
	public void delete(Long id) {
		
		LOGGER.info("Deleting Seller by Id...." + id);

		sellerRepository.deleteById(id);;
	}

	@Override
	public List<Seller> getSellers() {

		LOGGER.info("Retrieve All sellers....");

 		return (List<Seller>) sellerRepository.findAll();
	}

	@Override
	public Optional<Seller> getSellerById(Long id) {

		LOGGER.info("Retrieve seller Id ...." + id);
		
		return sellerRepository.findById(id);
	}

	
    
}
