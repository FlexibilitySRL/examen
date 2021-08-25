package ar.com.plug.examen.domain.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.data.entity.Factura;
import ar.com.plug.examen.data.entity.FacturaDetalle;
import ar.com.plug.examen.domain.dto.FacturaDTO;
import ar.com.plug.examen.domain.dto.FacturaDetalleDTO;

@Component
public class FacturaMapper {

	public FacturaDTO mapFacturaToFacturaDTO(Factura factura)  { 		

		FacturaDTO facturaDTO  = new FacturaDTO(factura.getIdFactura(),factura.getIdCliente(),factura.getIdVendedor(),factura.getCodigo(),
				factura.getFecha(),factura.getTotal(),factura.isEstado(), null);

		return facturaDTO;
	}


	public Factura mapFacturaDTOToFactura(FacturaDTO facturaDTO){

		Factura  factura = new Factura(facturaDTO.getIdFactura(),facturaDTO.getIdCliente(),facturaDTO.getIdVendedor(),facturaDTO.getCodigo(),
				new java.sql.Date(facturaDTO.getFecha().getTime()),facturaDTO.getTotal(), false,null);

		return  factura;	
	}

	public List<FacturaDetalle> mapListaFacturaDetalleDTOToListaFacturaDetalle( List<FacturaDetalleDTO> listaFacturaDetalleDTO){

		BigDecimal total= new BigDecimal("0");
		List<FacturaDetalle> listaFacturaDetalle = new ArrayList<>();
		for (FacturaDetalleDTO facturaDetalleDTO : listaFacturaDetalleDTO) {
			FacturaDetalle fd = new FacturaDetalle(facturaDetalleDTO.getIdFDetalle(),facturaDetalleDTO.getIdProducto(),facturaDetalleDTO.getCantidad(),
					facturaDetalleDTO.getSubtotal(),new Factura());
			listaFacturaDetalle.add(fd);
			total = total.add( facturaDetalleDTO.getSubtotal());
		}

		return  listaFacturaDetalle ;
	}

	public List<FacturaDetalleDTO> mapListaFacturaDetalleToListaFacturaDetalleDTO( List<FacturaDetalle> listaFacturaDetalle){

		List<FacturaDetalleDTO> listaFacturaDetalleDTO = new ArrayList<>();
		for (FacturaDetalle facturaDetalle : listaFacturaDetalle) 
		{
			FacturaDetalleDTO facturaDetalleDTO_  = new FacturaDetalleDTO(facturaDetalle.getIdFDetalle(),facturaDetalle.getIdProducto(),facturaDetalle.getCantidad(),
					facturaDetalle.getSubtotal());
			listaFacturaDetalleDTO.add(facturaDetalleDTO_);

		}	
		return  listaFacturaDetalleDTO ;	
	}		
}
