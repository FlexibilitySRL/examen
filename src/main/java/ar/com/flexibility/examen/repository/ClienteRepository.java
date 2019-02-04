package ar.com.flexibility.examen.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.com.flexibility.examen.domain.model.*;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

}