package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.entities.Compra;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("compraRep")
public interface CompraR extends JpaRepository<Compra, Serializable>{
    
    public abstract Compra findById(int id);
    public abstract List<Compra> findByAprobado(boolean aprobado);
}
