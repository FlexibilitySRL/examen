package ar.com.flexibility.examen.rest.api.backend;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import ar.com.flexibility.examen.domain.service.InvoiceService;
import ar.com.flexibility.examen.factory.FacturaDTOFactory;
import ar.com.flexibility.examen.factory.InvoiceFactory;
import ar.com.flexibility.examen.rest.api.model.FacturaDTO;
import ar.com.flexibility.examen.rest.api.model.RespuestaDTO;

@Component
public class ComprasApiBackend {

	private final Logger logger = LoggerFactory.getLogger(ComprasApiBackend.class);

	@Autowired
	private InvoiceService invoiceService;

	/**
	 * Metodo que permite aprobar una compra
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<FacturaDTO> aprobarCompra(String id) {
		logger.debug("Inicia aprobacion de transaccion de compra con el id: {}" + id);
		FacturaDTO facturaDTO = FacturaDTOFactory.INSTANCE.from(invoiceService.approveBuy(Long.valueOf(id)));
		logger.debug("Finaliza aprobacion de transaccion de compra con el id: {}" + id);
		return new ResponseEntity<FacturaDTO>(facturaDTO, HttpStatus.OK);
	}

	/**
	 * Metodo que permite consultar informacion de compra
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<FacturaDTO> consultaCompra(String id) {
		logger.debug("Inicia consulta de compra con el id: {}" + id);
		FacturaDTO facturaDTO = FacturaDTOFactory.INSTANCE.from(invoiceService.getInvoiceById(Long.valueOf(id)));
		logger.debug("Finaliza consulta de compra con el id: {}" + id);
		return new ResponseEntity<FacturaDTO>(facturaDTO, HttpStatus.OK);
	}

	/**
	 * Metodo que permite consultar todas las facturas de compras
	 * 
	 * @return
	 */
	public ResponseEntity<List<FacturaDTO>> consultaCompras() {
		logger.debug("Inicia consulta de facturas de compra");
		List<FacturaDTO> facturasDTO = FacturaDTOFactory.INSTANCE.from(invoiceService.getAllInvoice());
		logger.debug("Finaliza consulta de facturas de compra");
		return new ResponseEntity<List<FacturaDTO>>(facturasDTO, HttpStatus.OK);
	}

	/**
	 * Metodo que permite inicializar la compra
	 * 
	 * @param body
	 * @return
	 * 
	 */
	public ResponseEntity<RespuestaDTO> crearCompra(FacturaDTO facturaDTO) {
		logger.debug("Inicia creacion de la factura");
		invoiceService.createBuy(InvoiceFactory.INSTANCE.from(facturaDTO));
		RespuestaDTO respuestaDTO = new RespuestaDTO();
		respuestaDTO.setCode(HttpStatus.OK.value());
		respuestaDTO.setMessage("Se creo la factura correctamente");
		logger.debug("Finaliza creacion de la factura");
		return new ResponseEntity<RespuestaDTO>(respuestaDTO, HttpStatus.OK);
	}

}
