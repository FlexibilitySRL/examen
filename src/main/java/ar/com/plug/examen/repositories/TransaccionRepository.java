package ar.com.plug.examen.repositories;
import ar.com.plug.examen.models.TransaccionModel;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends CrudRepository<TransaccionModel, Long>{

    @Override
    public abstract Optional<TransaccionModel> findById(Long id);
    
}