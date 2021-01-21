package ar.com.plug.examen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Seller;

@Repository
public interface SellerRepository extends CrudRepository<Seller,Long> {

}
