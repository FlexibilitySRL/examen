package ar.com.plug.examen.repositories;
import ar.com.plug.examen.models.ClienteModel;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<ClienteModel, Long>{

    @Override
    public abstract Optional<ClienteModel> findById(Long id);
    
}