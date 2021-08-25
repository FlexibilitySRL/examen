package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.dto.VendedorDTO;

public interface VendedorService {

	List<VendedorDTO> listarVendedores();
	VendedorDTO consultarVendedorPorId(long id);
	VendedorDTO crearVendedor(VendedorDTO vendedorDTO);
	VendedorDTO actualizarVendedor(VendedorDTO vendedorDTO);
	void eliminarVendedor(long id);

}
