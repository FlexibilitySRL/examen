package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.entities.Cliente;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("clienteRep")
public interface ClienteR extends JpaRepository<Cliente, Serializable>{

    public abstract Cliente findById(int id);
    public abstract List<Cliente> findByNombre(String nombre);
    public abstract List<Cliente> findByApellido(String apellido);
    public abstract Cliente findByNombreAndApellido(String nombre,String apellido);


}
