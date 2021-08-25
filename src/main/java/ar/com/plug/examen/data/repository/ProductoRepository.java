package ar.com.plug.examen.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.data.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
