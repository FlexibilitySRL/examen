package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.entities.Producto;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("productoRep")
public interface ProductoR extends JpaRepository<Producto, Serializable>{
    
    public abstract Producto findById(int id);
    public abstract Producto findByNombre(String nombre);
    public abstract List<Producto> findByCategoria(String categoria);
}
