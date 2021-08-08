package ar.com.plug.examen.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.dto.FacturaResponseDto;
import ar.com.plug.examen.domain.model.DetalleFactura;
import ar.com.plug.examen.domain.model.Factura;

@Component
public class FacturaToFacturaResponseDtoConverter implements Converter<Factura, FacturaResponseDto> {

	@Autowired
	private DetalleFacturaToDetalleFacturaResponseDtoConverter detalleConverter;
	
	@Override
	public FacturaResponseDto convert(Factura source) {
		FacturaResponseDto result = new FacturaResponseDto();
		result.setCliente(source.getCliente().getApellido() + ", " + source.getCliente().getNombre());
		result.setEstado(source.getEstado());
		result.setFecha(source.getFecha());
		result.setId(source.getId());
		for(DetalleFactura deachDetail :source.getDetalles()) {
			result.getDetalle().add(detalleConverter.convert(deachDetail));
			result.setTotal(result.getTotal() + deachDetail.getPrecio());
		}
		return result;
	}

}
