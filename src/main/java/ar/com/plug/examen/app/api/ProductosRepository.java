package ar.com.plug.examen.app.api;

import ar.com.plug.examen.domain.model.ProductosModel;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductosRepository extends CrudRepository<ProductosModel, Long>{
	public abstract ArrayList<ProductosModel> findMark(String mark);

   
}
