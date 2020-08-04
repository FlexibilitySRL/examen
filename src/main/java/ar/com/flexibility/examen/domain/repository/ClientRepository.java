package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.flexibility.examen.domain.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	@Modifying
	@Query ("delete from Client p where p.identifier=:identifier")
	int deleteByIdentifier(@Param("identifier") String identifier);

	Client getFirstByIdentifier(String identifier);

}
