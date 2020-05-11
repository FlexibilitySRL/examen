package ar.com.flexibility.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repositories.ISellerRepository;

@Service
public class ProcessSellerServiceImp  extends ProcessGenericEntityService<Seller>{
	
	@Autowired
	private ISellerRepository repository;

	@Override
	public JpaRepository<Seller, Long> getRepository() {
		return repository;
	}

	@Override
	public Class<ProcessSellerServiceImp> getClazz() {
		return ProcessSellerServiceImp.class;
	}

	@Override
	public String getEntityName() {
		return "Seller";
	}
	
	@Override
	public Seller update(Seller seller){
		
		if(repository.exists(seller.getId())){
			seller = super.update(seller);
		}else{
			logger.info(String.format("update no existe ID: %s", seller.getId()));
		}
		
		return seller;		
	}
	
}
