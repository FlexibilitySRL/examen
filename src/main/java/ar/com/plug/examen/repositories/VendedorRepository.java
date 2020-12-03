package ar.com.plug.examen.repositories;
import ar.com.plug.examen.models.VendedorModel;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends CrudRepository<VendedorModel, Long>{

    @Override
    public abstract Optional<VendedorModel> findById(Long id);
    
}