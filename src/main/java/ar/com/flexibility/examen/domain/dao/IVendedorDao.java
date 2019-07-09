package ar.com.flexibility.examen.domain.dao;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.model.entity.Vendedor;

public interface IVendedorDao extends CrudRepository<Vendedor, Long> {

}