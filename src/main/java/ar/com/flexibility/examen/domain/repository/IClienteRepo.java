package ar.com.flexibility.examen.domain.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Cliente;

@Repository("repositorio")
public interface IClienteRepo extends JpaRepository<Cliente, Serializable>{

	public List<Cliente> findAll();
	public abstract Cliente findByNombre (String nombre);
	public abstract Cliente findByEmpresa (String empresa);

}
