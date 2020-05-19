package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.model.Seller;

public interface SellerRepository extends CrudRepository<Seller, Long> {

}
