package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.flexibility.examen.domain.model.Cliente;


public interface IClienteRepo extends JpaRepository<Cliente, Integer>{



}
