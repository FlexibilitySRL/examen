package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author AGB
 */
public interface ClientRepository extends JpaRepository<ClientModel, Integer> {

    public ClientModel findByEmail(String email);
    
}
