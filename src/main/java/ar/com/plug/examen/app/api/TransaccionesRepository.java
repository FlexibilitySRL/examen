package ar.com.plug.examen.app.api;

import ar.com.plug.examen.domain.model.TransaccionModel;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;


@Repository
public interface TransaccionesRepository extends CrudRepository<TransaccionModel, Long>{

	
	@Query(nativeQuery = true,value = "SELECT * FROM transacciones WHERE datePurch = :datePurch");
	ArrayList<TransaccionModel> findTransactionsDate(@Param("datePurch") Date datePurch);

	
	@Query(nativeQuery = true,value = "SELECT * FROM transacciones WHERE client IN ( SELECT id FROM cliente WHERE dni =  :client.getDni() + )");
	ArrayList<TransaccionModel> findByTransactionClient(@Param("client") ClienteModel client);

   
}