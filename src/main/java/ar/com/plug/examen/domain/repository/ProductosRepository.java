package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Productos;

public interface ProductosRepository  extends JpaRepository<Productos, Integer>{

}
