package ar.com.plug.examen.app.api;

import ar.com.plug.examen.domain.model.ClienteModel;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends CrudRepository<ClienteModel, Long>{
	public abstract ArrayList<ClienteModel> findId(Long id);

   
}