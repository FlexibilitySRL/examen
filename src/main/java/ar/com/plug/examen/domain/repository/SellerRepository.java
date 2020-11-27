package ar.com.plug.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.domain.model.Seller;

public interface SellerRepository extends CrudRepository<Seller, Long > {

}
