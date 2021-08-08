package ar.com.plug.examen.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.dto.DetalleFacturaResponseDto;
import ar.com.plug.examen.domain.model.DetalleFactura;

@Component
public class DetalleFacturaToDetalleFacturaResponseDtoConverter implements Converter<DetalleFactura, DetalleFacturaResponseDto> {

	@Override
	public DetalleFacturaResponseDto convert(DetalleFactura source) {
		DetalleFacturaResponseDto result = new DetalleFacturaResponseDto();
		result.setCantidada(source.getCantidad());
		result.setProducto(source.getProducto().getNombre());
		result.setPrecioUnitario(source.getProducto().getPrecioUnitario());
		return result;
	}

}
