package ar.com.plug.examen.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Producto;

public interface ProductoDao extends JpaRepository<Producto, Integer>{

}
