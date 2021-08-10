package ar.com.plug.examen.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.dto.TransaccionResponseDto;
import ar.com.plug.examen.domain.model.DetalleTransaccion;
import ar.com.plug.examen.domain.model.Transaccion;

@Component
public class TransaccionToTransaccionResponseDtoConverter implements Converter<Transaccion, TransaccionResponseDto> {

	@Autowired
	private DetalleTransaccionToDetalleTransaccionResponseDtoConverter detalleConverter;
	
	@Override
	public TransaccionResponseDto convert(Transaccion source) {
		TransaccionResponseDto result = new TransaccionResponseDto();
		result.setCliente(source.getCliente().getApellido() + ", " + source.getCliente().getNombre());
		result.setVendedor(source.getVendedor().getApellido() + ", " + source.getVendedor().getNombre());
		result.setEstado(source.getEstado());
		result.setFecha(source.getFecha());
		result.setId(source.getId());
		for(DetalleTransaccion deachDetail :source.getDetalles()) {
			result.getDetalle().add(detalleConverter.convert(deachDetail));
			result.setTotal(result.getTotal() + deachDetail.getPrecio());
		}
		return result;
	}

}
