package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.dto.FacturaDTO;

public interface FacturaService {	
	FacturaDTO crearFactura(FacturaDTO facturaDTO);
	FacturaDTO aprobarFactura(long id);
	List<FacturaDTO> listarFacturas();
}
