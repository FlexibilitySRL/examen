package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>
{
	Page<Client> findAllByActiveTrue(Pageable pageable);
	Client findByDocument(String document);
}
