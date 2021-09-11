package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.clientModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface clientRepository extends CrudRepository<clientModel, Integer> {


}
