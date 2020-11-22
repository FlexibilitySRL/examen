package ar.com.plug.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Producto;

public interface ProductoService extends JpaRepository<Producto, Long> {

}
