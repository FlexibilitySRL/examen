package ar.com.flexibility.examen.domain.dao;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.model.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}