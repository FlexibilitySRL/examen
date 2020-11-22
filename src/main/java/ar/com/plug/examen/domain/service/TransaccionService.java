package ar.com.plug.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import ar.com.plug.examen.domain.model.Transaccion;

public interface TransaccionService extends JpaRepository<Transaccion, Long>, QueryByExampleExecutor<Transaccion> {

	Transaccion findByCodigoTrx(String codigoTrx);
}
