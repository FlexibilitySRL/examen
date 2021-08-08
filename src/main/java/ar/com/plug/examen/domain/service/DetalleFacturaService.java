package ar.com.plug.examen.domain.service;

import java.util.Optional;

import ar.com.plug.examen.domain.dto.DetalleFacturaAltaDto;
import ar.com.plug.examen.domain.dto.DetalleFacturaUpdateDto;
import ar.com.plug.examen.domain.model.DetalleFactura;

public interface DetalleFacturaService {
	
	public Optional<DetalleFactura> findById(Integer id);

	public DetalleFactura createInvoiceDetail(DetalleFacturaAltaDto d);
	
	public DetalleFactura updateInvoiceDetail(DetalleFacturaUpdateDto dto);
	
	public void deleteInvoiceDetail(Integer id);

	
}