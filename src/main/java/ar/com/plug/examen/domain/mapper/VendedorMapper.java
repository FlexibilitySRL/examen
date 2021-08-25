package ar.com.plug.examen.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.data.entity.Vendedor;
import ar.com.plug.examen.domain.dto.VendedorDTO;
import ar.com.plug.examen.domain.exception.CustomException;

@Component
public class VendedorMapper {


	public VendedorDTO mapClienteToClienteDTO(Vendedor vendedor)  {

		VendedorDTO VendedorDTO =  new VendedorDTO(vendedor.getIdVendedor(),vendedor.getNombre(),vendedor.getApellido(), 
				vendedor.getDireccion(),vendedor.getTelefono(),vendedor.getEmail(),vendedor.getCodigo());

		return VendedorDTO;
	}

	public Vendedor mapClienteDTOToCliente(VendedorDTO vendedorDTO) throws CustomException {

		Vendedor vendedor =  new Vendedor(vendedorDTO.getId(),vendedorDTO.getCodigo(),vendedorDTO.getNombre(), 
				vendedorDTO.getApellido(),vendedorDTO.getDireccion(),vendedorDTO.getEmail(),vendedorDTO.getTelefono());

		return vendedor;
	}

	public List<VendedorDTO> mapListVendedorToVendedorDTO(List<Vendedor> Vendedores){	
		List<VendedorDTO> listaVendedorDTO = new ArrayList<VendedorDTO>();

		for (Vendedor vendedor : Vendedores) {
			VendedorDTO vendedorDTO = mapClienteToClienteDTO(vendedor);
			listaVendedorDTO.add(vendedorDTO);			
		}

		return listaVendedorDTO;		
	}

}
