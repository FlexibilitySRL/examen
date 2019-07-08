package ar.com.flexibility.examen.domain.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Producto;

@Repository("repositorioProducto")
public interface IProductoRepo extends JpaRepository<Producto, Serializable>{

	public List<Producto> findAll();
	public abstract Producto findByProducto (String producto);
	public abstract Producto findById(int id);

}
