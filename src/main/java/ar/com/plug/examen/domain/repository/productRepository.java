package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.productModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends CrudRepository<productModel, Integer> {


}
