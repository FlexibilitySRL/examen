package ar.com.plug.examen.domain.service;

import java.util.List;
import java.util.Optional;

import ar.com.plug.examen.domain.dto.FacturaAltaDto;
import ar.com.plug.examen.domain.dto.FacturaUpdateDto;
import ar.com.plug.examen.domain.exception.ServiceException;
import ar.com.plug.examen.domain.model.Factura;
import ar.com.plug.examen.domain.util.EstadoFactura;

public interface FacturaService {

	public List<Factura> getInvoices();
	
	public Optional<Factura> findById(Integer id);

	public Factura createInvoice(FacturaAltaDto request);
	
	public Factura updateInvoice(FacturaUpdateDto request);
	
	public void deleteInvoice(Integer id);
	
	public Factura approbe(Integer id);
	
	public Factura cancel(Integer id);
	
}
