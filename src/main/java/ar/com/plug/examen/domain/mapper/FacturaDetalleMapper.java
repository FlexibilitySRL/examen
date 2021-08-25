package ar.com.plug.examen.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.data.entity.Factura;
import ar.com.plug.examen.data.entity.FacturaDetalle;
import ar.com.plug.examen.domain.dto.FacturaDetalleDTO;

@Component
public class FacturaDetalleMapper {
	
	public FacturaDetalleDTO mapFacturaDetalleToFacturaDetalleDTO(FacturaDetalle facturaDetalle )  {

		FacturaDetalleDTO facturaDTO =  new FacturaDetalleDTO(facturaDetalle.getIdFDetalle(),facturaDetalle.getIdProducto(),facturaDetalle.getCantidad(), 
				facturaDetalle.getSubtotal());

		return facturaDTO;
	}

	public FacturaDetalle mapFacturaDetalleDTOToFactura(FacturaDetalleDTO facturaDetalleDTO,Factura factura)  {

		FacturaDetalle facturaDetalle =  new FacturaDetalle(facturaDetalleDTO.getIdFDetalle(),facturaDetalleDTO.getIdProducto(),facturaDetalleDTO.getCantidad(), 
				facturaDetalleDTO.getSubtotal(), factura);

		return facturaDetalle;
	}

	public List<FacturaDetalleDTO> mapListFacturaToFacturaDetalleDTO(List<FacturaDetalle> listaFacturaDetalle){	
		List<FacturaDetalleDTO> listaFacturaDetalleDTO = new ArrayList<FacturaDetalleDTO>();

		for (FacturaDetalle facturaDetalle : listaFacturaDetalle) {
			FacturaDetalleDTO facturaDTO = mapFacturaDetalleToFacturaDetalleDTO(facturaDetalle);
			listaFacturaDetalleDTO.add(facturaDTO);			
		}

		return listaFacturaDetalleDTO;		
	}

}
