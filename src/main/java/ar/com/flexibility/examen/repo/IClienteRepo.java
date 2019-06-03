package ar.com.flexibility.examen.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Cliente;

@Repository
public interface IClienteRepo extends JpaRepository<Cliente, Integer>{

}
 	