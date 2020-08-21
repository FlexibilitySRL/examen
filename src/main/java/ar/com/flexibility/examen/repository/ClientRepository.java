package ar.com.flexibility.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

	@Query(value = "SELECT * FROM CLIENT WHERE ID = ?1", nativeQuery = true)
	Client findById(Long id);

}
