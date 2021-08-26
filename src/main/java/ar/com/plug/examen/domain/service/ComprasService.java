package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.ResponseDTO;
import ar.com.plug.examen.domain.model.Compras;

public interface ComprasService {
	
	public ResponseDTO saveCompra(Compras compras);

	public ResponseDTO deleteCompra(Integer id);

	public ResponseDTO findByIdcliente(Integer id);
}
