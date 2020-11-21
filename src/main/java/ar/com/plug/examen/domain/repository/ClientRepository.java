package ar.com.plug.examen.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findOneById(Long id);
	
	@Query(value = "SELECT p FROM Client p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Client> findByName(@Param("name") String name);
}