package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.LogTransation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogTransationRepository extends JpaRepository<LogTransation, Long> {
}
