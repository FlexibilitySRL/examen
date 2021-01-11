package ar.com.plug.examen.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	Optional<Client> findById(Long id);
}
