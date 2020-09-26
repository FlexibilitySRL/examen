package ar.com.flexibility.examen.factory;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ar.com.flexibility.examen.domain.model.db.Invoice;
import ar.com.flexibility.examen.domain.model.db.InvoiceDetail;
import ar.com.flexibility.examen.domain.util.Contanst;
import ar.com.flexibility.examen.rest.api.model.DetalleFacturaDTO;
import ar.com.flexibility.examen.rest.api.model.FacturaDTO;

@Mapper(imports = Contanst.class, uses = { ClienteDTOFactory.class, DetalleFacturaDTOFactory.class })
public interface FacturaDTOFactory {

	static FacturaDTOFactory INSTANCE = Mappers.getMapper(FacturaDTOFactory.class);

	@Mapping(target = "udid", source = "id")
	@Mapping(target = "subtotal", source = "subtotal")
	@Mapping(target = "total", source = "total")
	@Mapping(target = "fecha", expression = "java(FacturaDTOFactory.localDateTimeToString(invoice.getCreated()))")
	@Mapping(target = "estado", source = "state")
	@Mapping(target = "cliente", source = "client")
	@Mapping(target = "detalle", source = "invoiceDetail")
	FacturaDTO from(Invoice invoice);

	List<FacturaDTO> from(List<Invoice> invoices);

	static String localDateTimeToString(LocalDateTime localDateTime) {
		return localDateTime.format(Contanst.DATE_TIME_FORMATTER);
	}

}
