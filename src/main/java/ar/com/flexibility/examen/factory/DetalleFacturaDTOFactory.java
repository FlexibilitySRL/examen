package ar.com.flexibility.examen.factory;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.com.flexibility.examen.domain.model.db.InvoiceDetail;
import ar.com.flexibility.examen.rest.api.model.DetalleFacturaDTO;

@Mapper
public interface DetalleFacturaDTOFactory {
	@Mapping(target = "cantidad", source = "quantity")
	@Mapping(target = "valor", source = "value")
	@Mapping(target = "identificadorProducto", source = "invoiceDetail.product.id")
	DetalleFacturaDTO from(InvoiceDetail invoiceDetail);

	List<DetalleFacturaDTO> from(List<InvoiceDetail> invoicesDetail);

}
