package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.sellerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sellerRepository extends CrudRepository<sellerModel, Integer> {



}
