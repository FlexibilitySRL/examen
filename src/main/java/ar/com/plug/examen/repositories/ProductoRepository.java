package ar.com.plug.examen.repositories;
import ar.com.plug.examen.models.ProductoModel;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoModel, Long>{

    @Override
    public abstract Optional<ProductoModel> findById(Long id);
    
}