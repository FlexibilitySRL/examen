package ar.com.flexibility.examen.factory;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ar.com.flexibility.examen.domain.model.db.Invoice;
import ar.com.flexibility.examen.domain.model.db.InvoiceDetail;
import ar.com.flexibility.examen.rest.api.model.DetalleFacturaDTO;
import ar.com.flexibility.examen.rest.api.model.FacturaDTO;

@Mapper
public interface InvoiceFactory {

	static InvoiceFactory INSTANCE = Mappers.getMapper(InvoiceFactory.class);

	@Mapping(source = "cliente.udid", target = "client.id")
	@Mapping(source = "subtotal", target = "subtotal")
	@Mapping(source = "total", target = "total")
	@Mapping(source = "detalle", target = "invoiceDetail")
	Invoice from(FacturaDTO facturaDTO);

	@Mapping(source = "cantidad", target = "quantity")
	@Mapping(source = "valor", target = "value")
	@Mapping(source = "identificadorProducto", target = "product.id")
	InvoiceDetail from(DetalleFacturaDTO detalleFacturaDTO);

	List<InvoiceDetail> from(List<DetalleFacturaDTO> detalleFacturaDTO);
}
