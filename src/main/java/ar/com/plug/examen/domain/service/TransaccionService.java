package ar.com.plug.examen.domain.service;

import java.util.List;
import java.util.Optional;

import ar.com.plug.examen.domain.dto.TransaccionAltaDto;
import ar.com.plug.examen.domain.dto.TransaccionUpdateDto;
import ar.com.plug.examen.domain.exception.ServiceException;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.util.EstadoTransaccion;

public interface TransaccionService {

	public List<Transaccion> getTransactions();
	
	public Optional<Transaccion> findById(Integer id);

	public Transaccion createTransaction(TransaccionAltaDto request);
	
	public Transaccion updateTransaction(TransaccionUpdateDto request);
	
	public void deleteTransaction(Integer id);
	
	public Transaccion approbe(Integer id);
	
	public Transaccion cancel(Integer id);
	
}
