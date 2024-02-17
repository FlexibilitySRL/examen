package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraderRepository  extends JpaRepository<Trader, Long> {
}
