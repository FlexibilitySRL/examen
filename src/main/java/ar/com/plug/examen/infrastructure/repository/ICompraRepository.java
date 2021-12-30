package ar.com.plug.examen.infrastructure.repository;

import ar.com.plug.examen.domain.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompraRepository extends JpaRepository<Compra, Integer> {

}