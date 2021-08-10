package ar.com.plug.examen.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.dto.DetalleTransaccionResponseDto;
import ar.com.plug.examen.domain.model.DetalleTransaccion;

@Component
public class DetalleTransaccionToDetalleTransaccionResponseDtoConverter implements Converter<DetalleTransaccion, DetalleTransaccionResponseDto> {

	@Override
	public DetalleTransaccionResponseDto convert(DetalleTransaccion source) {
		DetalleTransaccionResponseDto result = new DetalleTransaccionResponseDto();
		result.setCantidada(source.getCantidad());
		result.setProducto(source.getProducto().getNombre());
		result.setPrecioUnitario(source.getProducto().getPrecioUnitario());
		return result;
	}

}
