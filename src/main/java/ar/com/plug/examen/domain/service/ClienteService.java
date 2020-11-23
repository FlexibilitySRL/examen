package ar.com.plug.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import ar.com.plug.examen.domain.model.Cliente;

public interface ClienteService extends JpaRepository<Cliente, Long>, QueryByExampleExecutor<Cliente> {

	/**
	 * Metodo que permite consultar Cliente por medio de numero documento
	 * @param numeroDocumento
	 * @return
	 */
	Cliente findByNumeroDocumento(String numeroDocumento);
}
