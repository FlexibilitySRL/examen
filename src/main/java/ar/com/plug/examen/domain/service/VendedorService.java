package ar.com.plug.examen.domain.service;

import java.util.List;
import java.util.Optional;

import ar.com.plug.examen.domain.dto.VendedorAltaDto;
import ar.com.plug.examen.domain.dto.VendedorUpdateDto;
import ar.com.plug.examen.domain.model.Vendedor;

public interface VendedorService {
	
	public List<Vendedor> getSellers();
	
	public Optional<Vendedor> findById(Integer id);

	public Vendedor createSeller(VendedorAltaDto d);
	
	public Vendedor updateSeller(VendedorUpdateDto dto);
	
	public void deleteSeller(Integer id);
	
}
