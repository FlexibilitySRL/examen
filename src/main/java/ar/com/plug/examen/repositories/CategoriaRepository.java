package ar.com.plug.examen.repositories;
import ar.com.plug.examen.models.CategoriaModel;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<CategoriaModel, Long>{

    @Override
    public abstract Optional<CategoriaModel> findById(Long id);
    
}