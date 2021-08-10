package ar.com.plug.examen.domain.service;

import java.util.Optional;

import ar.com.plug.examen.domain.dto.DetalleTransaccionAltaDto;
import ar.com.plug.examen.domain.dto.DetalleTransaccionUpdateDto;
import ar.com.plug.examen.domain.model.DetalleTransaccion;

public interface DetalleTransaccionService {
	
	public Optional<DetalleTransaccion> findById(Integer id);

	public DetalleTransaccion createTransactionDetail(DetalleTransaccionAltaDto d);
	
	public DetalleTransaccion updateTransactionDetail(DetalleTransaccionUpdateDto dto);
	
	public void deleteTransactionDetail(Integer id);

	
}