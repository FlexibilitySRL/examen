package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.dto.CompraRestDto;

public interface ICompraService {

	 public CompraRestDto updateCompra(CompraRestDto compraRestDto);
	 
	 public List<CompraRestDto> getCompras();

	 public CompraRestDto getCompraById(Long idCompra);

	 public Boolean removeCompraById(Long id);

	 public Boolean aprobarCompraById(Long id);


}
