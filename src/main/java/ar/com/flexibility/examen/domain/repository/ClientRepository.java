package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
}
